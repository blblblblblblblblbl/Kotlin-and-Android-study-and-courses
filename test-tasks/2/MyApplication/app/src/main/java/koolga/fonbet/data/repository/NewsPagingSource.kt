package koolga.fonbet.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import koolga.fonbet.data.data_classes.api.Articles
import javax.inject.Inject

class NewsPagingSource @Inject constructor(
    private val repository: Repository
): PagingSource<Int, Articles>() {
    override fun getRefreshKey(state: PagingState<Int, Articles>): Int?= FIRST_PAGE
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Articles> {
        val page = params.key?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getNewsPage(page)
        }.fold(
            onSuccess = {

                Log.d("MyLog",it.toString())
                if (it.articles.size==0){
                    LoadResult.Page(
                        data = listOf<Articles>(),
                        prevKey = null,
                        nextKey =  null
                    )
                }
                else{
                    it?.let { it1 ->
                        LoadResult.Page(
                            data = it1.articles,
                            prevKey = null,
                            nextKey =  page+1
                        )
                    }
                }

            },
            onFailure = { LoadResult.Error(it) }
        )!!
    }
    companion object{
        private val FIRST_PAGE = 1
    }
}