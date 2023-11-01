package com.juanpoveda.cocktails.presentation.cocktaildetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanpoveda.cocktails.domain.model.fold
import com.juanpoveda.cocktails.domain.usecase.GetIngredientUseCase
import com.juanpoveda.cocktails.presentation.model.UiCocktail
import com.juanpoveda.cocktails.presentation.model.UiIngredient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CocktailDetailViewModel @Inject constructor(
    private val getIngredientUseCase: GetIngredientUseCase
) : ViewModel() {

    private val _cocktailDetailsUiState: MutableStateFlow<CocktailDetailUiState> = MutableStateFlow(CocktailDetailUiState.Loading)
    val cocktailDetailsUiState: StateFlow<CocktailDetailUiState>
        get() = _cocktailDetailsUiState

    private val _ingredientsUiState: MutableStateFlow<IngredientsUiState> = MutableStateFlow(IngredientsUiState.Loading)
    val ingredientsUiState: StateFlow<IngredientsUiState>
        get() = _ingredientsUiState

    fun init(cocktail: UiCocktail) {
        _cocktailDetailsUiState.value = CocktailDetailUiState.Success(cocktail)
    }

    fun getIngredient(ingredientName: String) {
        getIngredientUseCase(ingredientName.trim()).map { result ->
            result.fold(
                onSuccess = { _ingredientsUiState.value = IngredientsUiState.Success(it) },
                onFailure = { _ingredientsUiState.value = IngredientsUiState.Error(it.message) }
            )
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }

    fun clearIngredient() {
        _ingredientsUiState.value = IngredientsUiState.Loading
    }

}

sealed class CocktailDetailUiState {

    object Loading : CocktailDetailUiState()
    data class Success(val uiCocktail: UiCocktail) : CocktailDetailUiState()
    data class Error(val message: String) : CocktailDetailUiState()

}

sealed class IngredientsUiState {

    object Loading : IngredientsUiState()
    data class Success(val ingredient: UiIngredient) : IngredientsUiState()
    data class Error(val message: String) : IngredientsUiState()

}
