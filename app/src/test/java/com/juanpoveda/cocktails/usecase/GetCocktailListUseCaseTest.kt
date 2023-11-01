package com.juanpoveda.cocktails.usecase

import com.juanpoveda.cocktails.repository.FakeCocktailsRepository
import com.juanpoveda.cocktails.domain.mapper.CocktailMapper
import com.juanpoveda.cocktails.domain.model.fold
import com.juanpoveda.cocktails.domain.usecase.GetCocktailListUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class GetCocktailListUseCaseTest {
    private lateinit var getCocktailListUseCase: GetCocktailListUseCase
    private lateinit var fakeCocktailsRepository: FakeCocktailsRepository

    @Before
    fun setUp(){
        fakeCocktailsRepository = FakeCocktailsRepository()
        getCocktailListUseCase = GetCocktailListUseCase(fakeCocktailsRepository, CocktailMapper())
    }

    @Test
    fun `Get Cocktails List, return correct number of list items` (): Unit = runBlocking{
        val cocktails = getCocktailListUseCase(forceRefresh = true).first()
        cocktails.fold(
            onSuccess = { cocktails ->
                assertEquals(2, cocktails.size)
            },
            onFailure = { exception ->
                assert(exception.message.isNullOrEmpty())
            }
        )
    }

    @Test
    fun `Get Cocktails List, return correct cocktail list` (): Unit = runBlocking{
        val cocktails = getCocktailListUseCase(forceRefresh = true).first()
        cocktails.fold(
            onSuccess = { cocktails ->
                assertEquals("Margarita", cocktails[0].name)
                assertEquals("Old Fashioned", cocktails[1].name)
            },
            onFailure = { exception ->
                assert(exception.message.isNullOrEmpty())
            }
        )
    }

    @Test
    fun `Get Cocktails List, return incorrect cocktail list` (): Unit = runBlocking{
        val cocktails = getCocktailListUseCase(forceRefresh = true).first()
        cocktails.fold(
            onSuccess = { cocktails ->
                assertNotEquals("Margarita", cocktails[1].name)
            },
            onFailure = { exception ->
                assert(exception.message.isNullOrEmpty())
            }
        )
    }
}
