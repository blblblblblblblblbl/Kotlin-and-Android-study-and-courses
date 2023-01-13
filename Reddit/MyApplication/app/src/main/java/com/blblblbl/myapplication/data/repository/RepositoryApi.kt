package com.blblblbl.myapplication.data.repository

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.blblblbl.myapplication.data.data_classes.responses.friends.FriendsResponse
import com.blblblbl.myapplication.data.data_classes.responses.me.MeResponse
import com.blblblbl.myapplication.data.data_classes.responses.posts.SubredditPostsResponse
import com.blblblbl.myapplication.data.data_classes.responses.subreddit.SubredditsResponse
import com.blblblbl.myapplication.data.data_classes.responses.user_info.UserInfoResponse
import com.blblblbl.myapplication.data.persistent_storage.PersistentStorage
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.*
import javax.inject.Inject

class RepositoryApi @Inject constructor(
    private val persistentStorage: PersistentStorage
) {
    object RetrofitServices{
        private const val BASE_URL= "https://oauth.reddit.com/"
        private val gson = GsonBuilder().setLenient().create()
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val authApi:AuthApi = retrofit.create(
            AuthApi::class.java
        )
        val subredditsApi:SubredditsApi = retrofit.create(
            SubredditsApi::class.java
        )
        val userApi:UserApi = retrofit.create(
            UserApi::class.java
        )

        interface AuthApi {
            @POST("api/v1/access_token")
            suspend fun exchangeCodeForToken()
        }
        interface SubredditsApi{
            @GET("subreddits/{where}")
            suspend fun getSubreddits(@Path("where") where:String, @Query("count") count:Int,@Query("limit") limit:Int, @Header("Authorization") authHeader:String):SubredditsResponse
            @GET("r/{subreddit}")
            suspend fun getSubredditPosts(@Path("subreddit") subreddit:String,@Header("Authorization") authHeader:String):SubredditPostsResponse
            @GET("/subreddits/search")
            suspend fun searchSubreddits(@Query("count") count:Int,@Query("limit") limit:Int,@Query("q") q:String, @Header("Authorization") authHeader:String):SubredditsResponse
        }
        interface UserApi{
            @GET("api/v1/me")
            suspend fun me(@Header("Authorization") authHeader:String): MeResponse
            @GET("/api/v1/me/friends")
            suspend fun friendsList(@Header("Authorization") authHeader:String):FriendsResponse
            @PUT("/api/v1/me/friends/{username}")
            suspend fun addToFriends(@Path("username") username:String,@Body requestBody: String,@Header("Authorization") authHeader:String)
            @GET("user/{username}/about")
            suspend fun user(@Path("username") userName: String,@Header("Authorization") authHeader:String): UserInfoResponse
        }
    }
    suspend fun getSubreddits(count: Int,limit: Int, where: String){
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        Log.d("MyLog","token from persistentStorage:$token")
        Log.d("MyLog","feed subreddits response:" + RetrofitServices.subredditsApi.getSubreddits(where,count, limit , "bearer $token"))
    }
    suspend fun getSubredditPosts(subreddit:String){
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        Log.d("MyLog","search response:" + RetrofitServices.subredditsApi.getSubredditPosts(subreddit, "bearer $token"))
    }
    suspend fun searchSubreddits(count: Int,limit: Int,search:String){
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        Log.d("MyLog","search response:" + RetrofitServices.subredditsApi.searchSubreddits(count, limit ,search, "bearer $token"))
    }
    suspend fun meInfo(){
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        Log.d("MyLog", "me response:" + RetrofitServices.userApi.me("bearer $token"))
    }
    suspend fun userInfo(userName:String){
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        Log.d("MyLog", "user response:" + RetrofitServices.userApi.user(userName,"bearer $token"))
    }
    suspend fun getFriends(){
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        Log.d("MyLog", "user response:" + RetrofitServices.userApi.friendsList("bearer $token"))
    }
    suspend fun addToFriends(userName:String){
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        RetrofitServices.userApi.addToFriends(userName,"{\"name\": \"$userName\"}","bearer $token")

    }
}