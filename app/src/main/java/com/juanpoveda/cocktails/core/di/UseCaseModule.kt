package com.juanpoveda.cocktails.core.di

import com.juanpoveda.cocktails.domain.mapper.CocktailMapper
import com.juanpoveda.cocktails.domain.repository.CocktailsRepository
import com.juanpoveda.cocktails.domain.usecase.GetCocktailListUseCase
import com.juanpoveda.cocktails.domain.usecase.GetIngredientUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideCocktailMapper(): CocktailMapper = CocktailMapper()

    @Provides
    fun provideGetCocktailListUseCase(cocktailsRepository: CocktailsRepository, cocktailMapper: CocktailMapper) =
        GetCocktailListUseCase(cocktailsRepository, cocktailMapper)

    @Provides
    fun provideGetIngredientUseCase(cocktailsRepository: CocktailsRepository, cocktailMapper: CocktailMapper) =
        GetIngredientUseCase(cocktailsRepository, cocktailMapper)

}
