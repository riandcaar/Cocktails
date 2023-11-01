package com.juanpoveda.cocktails.usecase

import com.juanpoveda.cocktails.repository.FakeCocktailsRepository
import com.juanpoveda.cocktails.domain.mapper.CocktailMapper
import com.juanpoveda.cocktails.domain.model.fold
import com.juanpoveda.cocktails.domain.usecase.GetIngredientUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetIngredientUseCaseTest {
    private lateinit var getIngredientUseCase: GetIngredientUseCase
    private lateinit var fakeCocktailsRepository: FakeCocktailsRepository

    @Before
    fun setUp(){
        fakeCocktailsRepository = FakeCocktailsRepository()
        getIngredientUseCase = GetIngredientUseCase(fakeCocktailsRepository, CocktailMapper())
    }

    @Test
    fun `Get existing Ingredient, return correct items` (): Unit = runBlocking{
        val ingredient = getIngredientUseCase("Vodka").first()
        ingredient.fold(
            onSuccess = { ingredient ->
                assertEquals("Vodka", ingredient.name)
            },
            onFailure = { exception ->
                assert(exception.message.isNullOrEmpty())
            }
        )
    }

}
