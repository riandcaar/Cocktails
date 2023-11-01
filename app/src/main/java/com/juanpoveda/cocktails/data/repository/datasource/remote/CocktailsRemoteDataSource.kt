package com.juanpoveda.cocktails.data.repository.datasource.remote

import com.juanpoveda.cocktails.data.model.CocktailsDTO
import com.juanpoveda.cocktails.data.model.IngredientsDTO
import retrofit2.Response

interface CocktailsRemoteDataSource {

    suspend fun getCocktailsList(): Response<CocktailsDTO>
    suspend fun getIngredient(ingredientName: String): Response<IngredientsDTO>

}
