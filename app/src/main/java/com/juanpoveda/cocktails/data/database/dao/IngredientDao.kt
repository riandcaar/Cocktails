package com.juanpoveda.cocktails.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juanpoveda.cocktails.data.database.entity.IngredientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredient order by name desc")
    fun getAll(): Flow<List<IngredientEntity>>

    @Query("SELECT * FROM ingredient WHERE UPPER(name) = :ingredientName")
    suspend fun getIngredientByName(ingredientName: String): IngredientEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ingredient: IngredientEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(ingredientList: List<IngredientEntity>)

    @Query("DELETE FROM ingredient")
    suspend fun deleteAll()

    @Query("SELECT EXISTS(SELECT * FROM ingredient WHERE UPPER(name) = :name)")
    suspend fun isIngredientInDb(name: String) : Boolean

}
