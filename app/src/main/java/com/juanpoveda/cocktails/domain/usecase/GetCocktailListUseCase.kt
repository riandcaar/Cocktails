package com.juanpoveda.cocktails.domain.usecase

import com.juanpoveda.cocktails.domain.mapper.CocktailMapper
import com.juanpoveda.cocktails.domain.model.Result
import com.juanpoveda.cocktails.domain.model.fold
import com.juanpoveda.cocktails.domain.repository.CocktailsRepository
import com.juanpoveda.cocktails.presentation.model.UiCocktail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCocktailListUseCase @Inject constructor(
    private val cocktailsRepository: CocktailsRepository,
    private val cocktailMapper: CocktailMapper
) {

    operator fun invoke(forceRefresh: Boolean): Flow<Result<List<UiCocktail>>> = flow {
        cocktailsRepository.getCocktailList().map { result ->
            result.fold(
                onSuccess = { cocktailsList ->
                    emit(Result.Success(cocktailsList.map { cocktailMapper.mapToUiCocktail(it) }))
                },
                onFailure = {
                    emit(Result.Error(it))
                }
            )
        }.collect()
    }

}
