package com.juanpoveda.cocktails.domain.usecase

import com.juanpoveda.cocktails.domain.mapper.CocktailMapper
import com.juanpoveda.cocktails.domain.model.Result
import com.juanpoveda.cocktails.domain.model.fold
import com.juanpoveda.cocktails.domain.repository.CocktailsRepository
import com.juanpoveda.cocktails.presentation.model.UiIngredient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetIngredientUseCase @Inject constructor(
    private val cocktailsRepository: CocktailsRepository,
    private val cocktailMapper: CocktailMapper
) {

    operator fun invoke(name: String): Flow<Result<UiIngredient>> = flow {
        cocktailsRepository.getIngredient(name).map { result ->
            result.fold(
                onSuccess = { ingredient ->
                    emit(Result.Success(cocktailMapper.mapToUiIngredient(ingredient)))
                },
                onFailure = {
                    emit(Result.Error(it))
                }
            )
        }.collect()
    }

}
