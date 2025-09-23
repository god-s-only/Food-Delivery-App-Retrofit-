package com.example.foodieapp.di

import android.content.Context
import com.example.foodieapp.common.Constants
import com.example.foodieapp.data.FoodAPI
import com.example.foodieapp.data.FoodhubSession
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFoodAPI(): FoodAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideFoodHubSession(@ApplicationContext context: Context): FoodhubSession{
        return FoodhubSession(context)
    }
}