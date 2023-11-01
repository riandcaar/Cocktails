package com.juanpoveda.cocktails.data.api

import com.juanpoveda.cocktails.data.model.CocktailsDTO
import com.juanpoveda.cocktails.data.model.IngredientsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheCocktailDBApi {

    @GET("search.php")
    suspend fun getCocktails(@Query("f") firstLetter: String = "a"): Response<CocktailsDTO>

    @GET("search.php")
    suspend fun getIngredient(@Query("i") ingredientName: String): Response<IngredientsDTO>

}
