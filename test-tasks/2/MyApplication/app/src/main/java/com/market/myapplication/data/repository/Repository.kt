package com.market.myapplication.data.repository

import com.market.myapplication.data.data_classes.api.NewsResponse
import javax.inject.Inject

class Repository @Inject constructor(
    private val repositoryApi: RepositoryApi
) {
    suspend fun getNewsPage(page:Int):NewsResponse{
        return repositoryApi.getNewsPage(page)
    }
}