package com.juanpoveda.cocktails.core.di

import android.content.Context
import com.juanpoveda.cocktails.data.api.TheCocktailDBApi
import com.juanpoveda.cocktails.data.database.dao.CocktailDao
import com.juanpoveda.cocktails.data.database.dao.IngredientDao
import com.juanpoveda.cocktails.data.mapper.CocktailResponseMapper
import com.juanpoveda.cocktails.data.repository.CocktailsRepositoryImpl
import com.juanpoveda.cocktails.data.repository.datasource.local.CocktailsLocalDataSource
import com.juanpoveda.cocktails.data.repository.datasource.local.CocktailsLocalDataSourceImpl
import com.juanpoveda.cocktails.data.repository.datasource.remote.CocktailsRemoteDataSource
import com.juanpoveda.cocktails.data.repository.datasource.remote.CocktailsRemoteDataSourceImpl
import com.juanpoveda.cocktails.data.utils.NetworkUtils
import com.juanpoveda.cocktails.domain.repository.CocktailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideCocktailsRemoteDataSource(theCocktailDBApi: TheCocktailDBApi): CocktailsRemoteDataSource {
        return CocktailsRemoteDataSourceImpl(theCocktailDBApi)
    }

    @Provides
    fun provideCocktailsLocalDataSource(cocktailDao: CocktailDao, ingredientDao: IngredientDao): CocktailsLocalDataSource {
        return CocktailsLocalDataSourceImpl(cocktailDao, ingredientDao)
    }

    @Provides
    fun provideCocktailResponseMapper(): CocktailResponseMapper = CocktailResponseMapper()

    @Provides
    fun provideNetworkUtils(@ApplicationContext context: Context): NetworkUtils =
        NetworkUtils(context)


    @Provides
    fun provideCocktailsRepository(
        cocktailsRemoteDataSource: CocktailsRemoteDataSource,
        cocktailsLocalDataSource: CocktailsLocalDataSource,
        cocktailResponseMapper: CocktailResponseMapper,
        networkUtils: NetworkUtils
    ): CocktailsRepository {
        return CocktailsRepositoryImpl(
            cocktailsRemoteDataSource,
            cocktailsLocalDataSource,
            cocktailResponseMapper,
            networkUtils
        )
    }
}
