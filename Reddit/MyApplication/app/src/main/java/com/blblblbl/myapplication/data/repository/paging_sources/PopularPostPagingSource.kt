package com.blblblbl.myapplication.data.repository.paging_sources

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.blblblbl.myapplication.data.data_classes.responses.posts.Post
import com.blblblbl.myapplication.data.repository.Repository
import javax.inject.Inject

class PopularPostPagingSource@Inject constructor(
    private val repository: Repository
): PagingSource<String, Post>() {
    override fun getRefreshKey(state: PagingState<String, Post>): String?= FIRST_PAGE
    override suspend fun load(params: LoadParams<String>): LoadResult<String, Post> {
        val page = params.key?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getPopularPosts(page)
        }.fold(
            onSuccess = {
                Log.d("MyLog",it.toString())
                if (it.data?.posts?.size==0){
                    LoadResult.Page(
                        data = listOf<Post>(),
                        prevKey = null,
                        nextKey =  null
                    )
                }
                else{
                    it?.data?.posts?.let { posts ->
                        LoadResult.Page(
                            data = posts,
                            prevKey = null,
                            nextKey =  it.data?.after?:""
                        )
                    }
                }

            },
            onFailure = { LoadResult.Error(it) }
        )!!
    }
    companion object{
        private val FIRST_PAGE = ""
    }
}