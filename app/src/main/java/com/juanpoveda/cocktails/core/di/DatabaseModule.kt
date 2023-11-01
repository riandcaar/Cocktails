package com.juanpoveda.cocktails.core.di

import android.content.Context
import androidx.room.Room
import com.juanpoveda.cocktails.data.database.CocktailsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCocktailsDatabase(@ApplicationContext context: Context): CocktailsDatabase {
        return Room.databaseBuilder(
            context,
            CocktailsDatabase::class.java,
            "cocktails_database"
        ).build()
    }

    @Provides
    fun provideCocktailDao(cocktailsDatabase: CocktailsDatabase) = cocktailsDatabase.cocktailDao()

    @Provides
    fun provideIngredientDao(cocktailsDatabase: CocktailsDatabase) = cocktailsDatabase.ingredientDao()

}
