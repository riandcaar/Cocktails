package com.juanpoveda.cocktails.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanpoveda.cocktails.domain.model.fold
import com.juanpoveda.cocktails.domain.usecase.GetCocktailListUseCase
import com.juanpoveda.cocktails.presentation.model.UiCocktail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CocktailsViewModel @Inject constructor(
    private val getCocktailListUseCase: GetCocktailListUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<CocktailListUiState> = MutableStateFlow(CocktailListUiState.Loading)
    val uiState: StateFlow<CocktailListUiState>
        get() = _uiState

    fun getCocktailList(forceRefresh: Boolean = false) {
        getCocktailListUseCase(forceRefresh).map { result ->
            result.fold(
                onSuccess = { cocktails ->
                    _uiState.value = CocktailListUiState.Success(cocktails)
                },
                onFailure = {
                    _uiState.value = CocktailListUiState.Error(it.message)
                })
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }

    sealed class CocktailListUiState {

        object Loading : CocktailListUiState()
        data class Success(val cocktails: List<UiCocktail>) : CocktailListUiState()
        data class Error(val message: String) : CocktailListUiState()

    }
}
