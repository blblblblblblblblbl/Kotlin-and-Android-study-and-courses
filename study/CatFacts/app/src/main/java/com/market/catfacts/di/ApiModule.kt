package com.market.catfacts.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.market.catfacts.service.api.CatFactService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule{

    @Singleton
    @Provides
    fun provideGsonBuilder():Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @Singleton
    @Provides
    fun provideCatFactService(retrofit: Retrofit.Builder):CatFactService=
        retrofit.build().create(CatFactService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson):Retrofit.Builder=
        Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .addConverterFactory(GsonConverterFactory.create(gson))
}