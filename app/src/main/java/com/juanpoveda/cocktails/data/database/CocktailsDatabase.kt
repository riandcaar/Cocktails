package com.juanpoveda.cocktails.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.juanpoveda.cocktails.data.database.dao.CocktailDao
import com.juanpoveda.cocktails.data.database.dao.IngredientDao
import com.juanpoveda.cocktails.data.database.entity.CocktailEntity
import com.juanpoveda.cocktails.data.database.entity.IngredientEntity
import com.juanpoveda.cocktails.data.utils.MapTypeConverter

@Database(
    entities = [CocktailEntity::class, IngredientEntity::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(MapTypeConverter::class)
abstract class CocktailsDatabase : RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao
    abstract fun ingredientDao(): IngredientDao

}
