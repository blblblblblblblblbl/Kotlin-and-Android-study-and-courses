package com.blblblbl.myapplication.viewModel

import android.graphics.pdf.PdfDocument.Page
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.blblblbl.myapplication.data.data_classes.responses.posts.Post
import com.blblblbl.myapplication.data.repository.Repository
import com.blblblbl.myapplication.data.repository.paging_sources.PopularPostPagingSource
import com.blblblbl.myapplication.data.repository.paging_sources.PostPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SubredditsFragmentViewModel @Inject constructor(
    private val repository: Repository,
    private val newPostPagingSource: PostPagingSource,
    private val popularPostPagingSource: PopularPostPagingSource,

): ViewModel(){
    private val _pagedPosts= MutableStateFlow<Flow<PagingData<Post>>?>(null)
    val pagedPosts = _pagedPosts.asStateFlow()
    fun loadNew(){
        _pagedPosts.value = Pager(
            config = PagingConfig(pageSize = 15),
            pagingSourceFactory = {newPostPagingSource}
        ).flow//.cachedIn(viewModelScope)
    }
    fun loadPopular(){
        _pagedPosts.value = Pager(
            config = PagingConfig(pageSize = 15),
            pagingSourceFactory = {popularPostPagingSource}
        ).flow//.cachedIn(viewModelScope)
    }
}