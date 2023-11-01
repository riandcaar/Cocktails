package com.juanpoveda.cocktails.viewmodel

import com.juanpoveda.cocktails.domain.model.DomainException
import com.juanpoveda.cocktails.domain.model.Result
import com.juanpoveda.cocktails.domain.usecase.GetCocktailListUseCase
import com.juanpoveda.cocktails.presentation.home.CocktailsViewModel
import com.juanpoveda.cocktails.presentation.model.UiCocktail
import com.juanpoveda.cocktails.presentation.model.UiIngredientMeasure
import com.juanpoveda.cocktails.util.MainCoroutineRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CocktailsViewModelTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var cocktailsViewModel: CocktailsViewModel

    private val getCocktailListUseCase: GetCocktailListUseCase = mockk()

    @Before
    fun setUp() {
        cocktailsViewModel = CocktailsViewModel(
            getCocktailListUseCase
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test get cocktail list success`() = mainCoroutineRule.runBlockingTest {
        // prepare
        val expected: List<UiCocktail> = buildList {
            add(UiCocktail(
                id = "2",
                name = "Old Fashioned",
                alternate = "OF",
                tags = "whiskey, orange, sweet",
                video = "",
                category = "Cocktail",
                IBA = "Classic",
                type = "Alcoholic",
                glass = "Rocks glass",
                instructions = "Add the ingredients in the glass with ice and stir for 20 seconds",
                instructionsES = "Add the ingredients in the glass with ice and stir for 20 seconds",
                thumb = "https://www.thecocktaildb.com/images/media/drink/u736bd1605907086.jpg",
                imageSource = "",
                dateModified = "2022-05-01 11:41:32",
                ingredients = listOf(UiIngredientMeasure(ingredientName = "Vodka", measure = "2oz"))
            ))
            add(UiCocktail(
                id = "1",
                name = "Margarita",
                alternate = "Marg",
                tags = "tequila, mexico, lime",
                video = "",
                category = "Cocktail",
                IBA = "Unforgettables",
                type = "Alcoholic",
                glass = "Cocktail glass",
                instructions = "Shake all the ingredients for 30 seconds, strain into a chilled cocktail glass",
                instructionsES = "Shake all the ingredients for 30 seconds, strain into a chilled cocktail glass",
                thumb = "https://www.thecocktaildb.com/images/media/drink/71t8581504353095.jpg",
                imageSource = "",
                dateModified = "2017-09-02 12:51:35",
                ingredients = listOf(UiIngredientMeasure(ingredientName = "Tequila", measure = "2oz"))
            ))
        }

        every { getCocktailListUseCase(any()) } returns flowOf(Result.Success(expected))

        // execute
        cocktailsViewModel.getCocktailList()

        // verify
        verify(exactly = 1) { getCocktailListUseCase(any()) }

        assertEquals(cocktailsViewModel.uiState.value, CocktailsViewModel.CocktailListUiState.Success(expected))
    }

    @Test
    fun `test get cocktail list  failure`() = mainCoroutineRule.runBlockingTest {
        // prepare
        val expected: String = "Something went wrong"

        every { getCocktailListUseCase(any()) } returns flowOf(Result.Error(DomainException(expected)))

        // execute
        cocktailsViewModel.getCocktailList()

        // verify
        verify(exactly = 1) { getCocktailListUseCase(any()) }

        assertEquals(cocktailsViewModel.uiState.value, CocktailsViewModel.CocktailListUiState.Error(expected))
    }

}
