package com.blblblbl.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blblblbl.myapplication.data.persistent_storage.PersistentStorage
import com.blblblbl.myapplication.data.repository.RepositoryApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubredditsFragmentViewModel @Inject constructor(
    private val repositoryApi: RepositoryApi
):ViewModel() {
    fun getSubreddits(){
        viewModelScope.launch {
            repositoryApi.getSubreddits(0,2,"new")
        }
    }
    fun getSubredditPosts(){
        viewModelScope.launch {
            val subreddit = "Home"
            repositoryApi.getSubredditPosts(subreddit)
        }
    }
    fun searchSubreddits(){
        viewModelScope.launch {
            repositoryApi.searchSubreddits(0,2,"dog")
        }
    }
    fun meInfo(){
        viewModelScope.launch {
            repositoryApi.meInfo()
        }
    }
    fun userInfo(){
        viewModelScope.launch {
            repositoryApi.userInfo("After_Solution1909")
        }
    }
    fun getFriends(){
        viewModelScope.launch {
            repositoryApi.getFriends()
        }
    }
    fun addToFriends(){
        viewModelScope.launch {
            val userName = "Quadaliacha"
            repositoryApi.addToFriends(userName)
        }
    }
    fun getSavedPosts(){
        val userName = "After_Solution1909"
        viewModelScope.launch {
            repositoryApi.getSavedPosts(userName)
        }
    }
    fun getSavedComments(){
        val userName = "After_Solution1909"
        viewModelScope.launch {
            repositoryApi.getSavedComments(userName)
        }
    }
}