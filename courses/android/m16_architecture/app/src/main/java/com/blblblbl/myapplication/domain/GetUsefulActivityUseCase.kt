package com.blblblbl.myapplication.domain

import com.blblblbl.myapplication.data.UsefulActivitiesRepository
import com.blblblbl.myapplication.entity.UsefulActivity
import javax.inject.Inject

class GetUsefulActivityUseCase @Inject constructor(
    val usefulActivitiesRepository: UsefulActivitiesRepository) {
    suspend fun execute():UsefulActivity{
        return usefulActivitiesRepository.getUsefulActivity()
    }
}