package com.juanpoveda.cocktails.data.repository.datasource.remote

import com.juanpoveda.cocktails.data.api.TheCocktailDBApi
import com.juanpoveda.cocktails.data.model.CocktailsDTO
import com.juanpoveda.cocktails.data.model.IngredientsDTO
import retrofit2.Response
import javax.inject.Inject

class CocktailsRemoteDataSourceImpl @Inject constructor(private val theCocktailDBApi: TheCocktailDBApi): CocktailsRemoteDataSource {
    override suspend fun getCocktailsList(): Response<CocktailsDTO> {
        return theCocktailDBApi.getCocktails()
    }
    override suspend fun getIngredient(ingredientName: String): Response<IngredientsDTO> {
        return theCocktailDBApi.getIngredient(ingredientName)
    }
}
