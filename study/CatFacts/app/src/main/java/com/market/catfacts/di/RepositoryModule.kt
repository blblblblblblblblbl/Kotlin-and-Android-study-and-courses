package com.market.catfacts.di

import com.market.catfacts.service.api.CatFactService
import com.market.catfacts.service.repository.CatFactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{
    @Singleton
    @Provides
    fun provideCatFactRepository(catFactService: CatFactService):CatFactRepository=
        CatFactRepository(catFactService)
}