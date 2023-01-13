package com.blblblbl.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
}