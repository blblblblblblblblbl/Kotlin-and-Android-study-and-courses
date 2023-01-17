package com.blblblbl.myapplication.data.repository

import android.util.Log
import com.blblblbl.myapplication.data.data_classes.responses.friends.FriendsResponse
import com.blblblbl.myapplication.data.data_classes.responses.me.MeResponse
import com.blblblbl.myapplication.data.data_classes.responses.posts.SubredditPostsResponse
import com.blblblbl.myapplication.data.data_classes.responses.saved.comments.SavedCommentsResponse
import com.blblblbl.myapplication.data.data_classes.responses.saved.link.SavedLinksResponse
import com.blblblbl.myapplication.data.persistent_storage.PersistentStorage
import javax.inject.Inject

class Repository @Inject constructor(
    private val repositoryApi: RepositoryApi,
    private val persistentStorage: PersistentStorage
) {
    suspend fun getNewPosts(page:String):SubredditPostsResponse{
        return repositoryApi.getNewPosts(page)
    }
    suspend fun getPopularPosts(page:String):SubredditPostsResponse{
        Log.d("MyLog","Repository getPopularPosts")
        return repositoryApi.getPopularPosts(page)
    }
    suspend fun meInfo():MeResponse{
        return repositoryApi.meInfo()
    }
    suspend fun getFriends(): FriendsResponse {
        return repositoryApi.getFriends()
    }
    suspend fun getSavedPosts(userName:String,page:String): SavedLinksResponse {
        Log.d("SavedPosts","page:$page")
        return repositoryApi.getSavedPosts(userName,page)
    }
    suspend fun getSavedComments(userName:String,page:String): SavedCommentsResponse {
        return repositoryApi.getSavedComments(userName,page)
    }
}