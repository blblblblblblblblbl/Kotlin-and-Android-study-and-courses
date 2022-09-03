package com.market.catfacts.service.repository

import com.market.catfacts.model.CatFact
import com.market.catfacts.service.api.CatFactService

class CatFactRepository(private val catFactService:CatFactService) {
    suspend fun getCatFact():CatFact = catFactService.getCatFact()
}