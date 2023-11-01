package com.juanpoveda.cocktails.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juanpoveda.cocktails.data.database.entity.CocktailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDao {

    @Query("SELECT * FROM cocktail order by name desc")
    fun getAll(): Flow<List<CocktailEntity>>

    @Query("SELECT * FROM cocktail WHERE id = :cocktailId")
    suspend fun getCocktailById(cocktailId: String): CocktailEntity?

    @Insert
    suspend fun insert(cocktail: CocktailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cocktailList: List<CocktailEntity>)

    @Query("DELETE FROM cocktail")
    suspend fun deleteAll()

}
