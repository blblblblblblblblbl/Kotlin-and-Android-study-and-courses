package com.market.catfacts.service.api

import com.market.catfacts.model.CatFact
import retrofit2.http.GET

interface CatFactService {
    @GET("fact")
    suspend fun getCatFact():CatFact
}