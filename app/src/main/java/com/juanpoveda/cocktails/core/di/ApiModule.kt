package com.juanpoveda.cocktails.core.di

import com.juanpoveda.cocktails.data.api.TheCocktailDBApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object ApiModule {

    @Provides
    fun provideBaseUrl(): String = "https://www.thecocktaildb.com/api/json/v1/1/"

    @Provides
    fun provideLoginInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    fun provideRetrofit(baseUrl: String, loggingInterceptor: HttpLoggingInterceptor): TheCocktailDBApi {

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(TheCocktailDBApi::class.java)
    }
}
