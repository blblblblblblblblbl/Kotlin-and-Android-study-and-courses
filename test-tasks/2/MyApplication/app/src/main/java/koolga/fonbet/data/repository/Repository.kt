package koolga.fonbet.data.repository

import koolga.fonbet.data.data_classes.api.NewsResponse
import javax.inject.Inject

class Repository @Inject constructor(
    private val repositoryApi: RepositoryApi
) {
    suspend fun getNewsPage(page:Int): NewsResponse {
        return repositoryApi.getNewsPage(page)
    }
}