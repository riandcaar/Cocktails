package com.juanpoveda.cocktails.data.repository.datasource.local

import com.juanpoveda.cocktails.data.database.dao.CocktailDao
import com.juanpoveda.cocktails.data.database.dao.IngredientDao
import com.juanpoveda.cocktails.data.database.entity.CocktailEntity
import com.juanpoveda.cocktails.data.database.entity.IngredientEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CocktailsLocalDataSourceImpl @Inject constructor(
    private val cocktailDao: CocktailDao,
    private val ingredientDao: IngredientDao
) : CocktailsLocalDataSource {

    override fun getAllCocktails(): Flow<List<CocktailEntity>> {
        return cocktailDao.getAll()
    }

    override suspend fun getCocktailById(cocktailId: String): CocktailEntity? {
        return cocktailDao.getCocktailById(cocktailId)
    }

    override suspend fun insert(cocktail: CocktailEntity) {
        cocktailDao.insert(cocktail)
    }

    override suspend fun insertAll(cocktailList: List<CocktailEntity>) {
        cocktailDao.insertAll(cocktailList)
    }

    override suspend fun deleteAll() {
        cocktailDao.deleteAll()
    }

    override suspend fun getIngredientByName(ingredientName: String): IngredientEntity? {
        return ingredientDao.getIngredientByName(ingredientName.uppercase())
    }

    override suspend fun isIngredientInDb(ingredientName: String): Boolean {
        return ingredientDao.isIngredientInDb(ingredientName.uppercase())
    }

    override suspend fun insertIngredient(ingredient: IngredientEntity) {
        ingredientDao.insert(ingredient)
    }

    override suspend fun insertAllIngredients(ingredientList: List<IngredientEntity>) {
        ingredientDao.insertAll(ingredientList)
    }

    override suspend fun deleteAllIngredients() {
        ingredientDao.deleteAll()
    }

}
