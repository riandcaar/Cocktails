package com.juanpoveda.cocktails.domain.mapper

import com.juanpoveda.cocktails.domain.model.Cocktail
import com.juanpoveda.cocktails.domain.model.Ingredient
import com.juanpoveda.cocktails.presentation.model.UiCocktail
import com.juanpoveda.cocktails.presentation.model.UiIngredient
import com.juanpoveda.cocktails.presentation.model.UiIngredientMeasure

class CocktailMapper {

    fun mapToUiCocktail(cocktail: Cocktail) = UiCocktail(
        id = cocktail.id,
        name = cocktail.name,
        alternate = cocktail.alternate,
        tags = cocktail.tags,
        video = cocktail.video,
        category = cocktail.category,
        IBA = cocktail.IBA,
        type = cocktail.type,
        glass = cocktail.glass,
        instructions = cocktail.instructions,
        instructionsES = cocktail.instructionsES,
        thumb = cocktail.thumb,
        dateModified = cocktail.dateModified,
        imageSource = cocktail.imageSource,
        ingredients = cocktail.ingredients.filterKeys { it != null }.map {entry ->
            UiIngredientMeasure(entry.key, entry.value)
        }
    )

    fun mapToUiIngredient(ingredient: Ingredient) = UiIngredient(
        id = ingredient.id,
        name = ingredient.name,
        description = ingredient.description,
        type = ingredient.type,
        isAlcoholic = ingredient.isAlcoholic,
        ABV = ingredient.ABV
    )
    
}
