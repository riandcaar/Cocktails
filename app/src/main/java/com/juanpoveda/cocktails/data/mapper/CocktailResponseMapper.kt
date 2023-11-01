package com.juanpoveda.cocktails.data.mapper

import com.juanpoveda.cocktails.data.database.entity.CocktailEntity
import com.juanpoveda.cocktails.data.database.entity.IngredientEntity
import com.juanpoveda.cocktails.data.model.CocktailDTO
import com.juanpoveda.cocktails.data.model.IngredientDTO
import com.juanpoveda.cocktails.domain.model.Cocktail
import com.juanpoveda.cocktails.domain.model.Ingredient

class CocktailResponseMapper {

    fun mapCocktailEntityToDomain(cocktailEntity: CocktailEntity): Cocktail {
        return Cocktail(
            id = cocktailEntity.id,
            name = cocktailEntity.name,
            alternate = cocktailEntity.alternate,
            tags = cocktailEntity.tags,
            video = cocktailEntity.video,
            category = cocktailEntity.category,
            IBA = cocktailEntity.IBA,
            type = cocktailEntity.type,
            glass = cocktailEntity.glass,
            instructions = cocktailEntity.instructions,
            instructionsES = cocktailEntity.instructionsES,
            thumb = cocktailEntity.drinkThumb,
            dateModified = cocktailEntity.dateModified,
            imageSource = cocktailEntity.imageSource,
            ingredients = cocktailEntity.ingredients
        )
    }

    fun mapCocktailResponseToEntity(cocktailDTO: CocktailDTO): CocktailEntity {
        return CocktailEntity(
            id = cocktailDTO.id,
            name = cocktailDTO.name,
            alternate = cocktailDTO.alternate,
            tags = cocktailDTO.tags,
            video = cocktailDTO.video,
            category = cocktailDTO.category,
            IBA = cocktailDTO.IBA,
            type = cocktailDTO.type,
            glass = cocktailDTO.glass,
            instructions = cocktailDTO.instructions,
            instructionsES = cocktailDTO.instructionsES,
            drinkThumb = cocktailDTO.strDrinkThumb,
            dateModified = cocktailDTO.dateModified,
            imageSource = cocktailDTO.imageSource,
            ingredients = hashMapOf(
                cocktailDTO.ingredient1 to cocktailDTO.measure1,
                cocktailDTO.ingredient2 to cocktailDTO.measure2,
                cocktailDTO.ingredient3 to cocktailDTO.measure3,
                cocktailDTO.ingredient4 to cocktailDTO.measure4,
                cocktailDTO.ingredient5 to cocktailDTO.measure5,
                cocktailDTO.ingredient6 to cocktailDTO.measure6,
                cocktailDTO.ingredient7 to cocktailDTO.measure7,
                cocktailDTO.ingredient8 to cocktailDTO.measure8,
                cocktailDTO.ingredient9 to cocktailDTO.measure9,
                cocktailDTO.ingredient10 to cocktailDTO.measure10,
                cocktailDTO.ingredient11 to cocktailDTO.measure11,
                cocktailDTO.ingredient12 to cocktailDTO.measure12,
                cocktailDTO.ingredient13 to cocktailDTO.measure13,
                cocktailDTO.ingredient14 to cocktailDTO.measure14,
                cocktailDTO.ingredient15 to cocktailDTO.measure15
            )
        )
    }

    fun mapIngredientEntityToDomain(ingredientEntity: IngredientEntity): Ingredient {
        return Ingredient(
            id = ingredientEntity.id,
            name = ingredientEntity.name,
            description = ingredientEntity.description,
            type = ingredientEntity.type,
            isAlcoholic = ingredientEntity.isAlcoholic,
            ABV = ingredientEntity.ABV
        )
    }

    fun mapIngredientResponseToEntity(ingredientDTO: IngredientDTO): IngredientEntity {
        return IngredientEntity(
            id = ingredientDTO.id,
            name = ingredientDTO.name,
            description = ingredientDTO.description,
            type = ingredientDTO.type,
            isAlcoholic = ingredientDTO.isAlcoholic,
            ABV = ingredientDTO.ABV
        )
    }

}
