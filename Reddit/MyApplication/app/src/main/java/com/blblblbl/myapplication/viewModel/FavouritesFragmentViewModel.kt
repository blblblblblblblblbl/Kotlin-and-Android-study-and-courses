package com.blblblbl.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.blblblbl.myapplication.data.data_classes.responses.friends.Friend
import com.blblblbl.myapplication.data.data_classes.responses.me.MeResponse
import com.blblblbl.myapplication.data.data_classes.responses.posts.Post
import com.blblblbl.myapplication.data.data_classes.responses.saved.comments.SavedComment
import com.blblblbl.myapplication.data.data_classes.responses.saved.link.SavedLink
import com.blblblbl.myapplication.data.repository.Repository
import com.blblblbl.myapplication.data.repository.paging_sources.PopularPostPagingSource
import com.blblblbl.myapplication.data.repository.paging_sources.PostPagingSource
import com.blblblbl.myapplication.data.repository.paging_sources.SavedCommentPagingSource
import com.blblblbl.myapplication.data.repository.paging_sources.SavedPostPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesFragmentViewModel @Inject constructor(
    private val repository: Repository,
    private val savedPostPagingSource: SavedPostPagingSource,
    private val savedCommentPagingSource: SavedCommentPagingSource,

    ): ViewModel(){
    private val _pagedPosts= MutableStateFlow<Flow<PagingData<SavedLink>>?>(null)
    private val _pagedComments= MutableStateFlow<Flow<PagingData<SavedComment>>?>(null)
    private val _userInfo = MutableStateFlow<MeResponse?>(null)
    val userInfo = _userInfo.asStateFlow()
    val pagedPosts = _pagedPosts.asStateFlow()
    val pagedComments = _pagedComments.asStateFlow()
    fun getUserInfo(){
        viewModelScope.launch {
            _userInfo.value = repository.meInfo()
        }

    }
    fun loadPosts(){
        viewModelScope.launch {
            if (_userInfo.value==null){
                _userInfo.value = repository.meInfo()
                _userInfo?.value?.name?.let { name->
                    savedPostPagingSource.usernameInit(name)
                    savedCommentPagingSource.usernameInit(name)
                }

            }
                _pagedPosts.value = Pager(
                    config = PagingConfig(pageSize = SavedPostPagingSource.PAGE_SIZE),
                    pagingSourceFactory = {savedPostPagingSource}
                ).flow
        }
    }
    fun loadComments(){
        viewModelScope.launch {
            if (_userInfo.value==null){
                _userInfo.value = repository.meInfo()
                _userInfo?.value?.name?.let { name->
                    savedPostPagingSource.usernameInit(name)
                    savedCommentPagingSource.usernameInit(name)
                }

            }
            _pagedComments.value = Pager(
                config = PagingConfig(pageSize = SavedCommentPagingSource.PAGE_SIZE),
                pagingSourceFactory = {savedCommentPagingSource}
            ).flow
        }

    }
}