package com.blblblbl.myapplication.data.repository

import android.util.Log
import com.blblblbl.myapplication.data.data_classes.responses.friends.FriendsResponse
import com.blblblbl.myapplication.data.data_classes.responses.me.MeResponse
import com.blblblbl.myapplication.data.data_classes.responses.posts.SubredditPostsResponse
import com.blblblbl.myapplication.data.data_classes.responses.posts.comments.PostCommentsResponse
import com.blblblbl.myapplication.data.data_classes.responses.posts.comments.utils.PostCommentsResponseDeserializer
import com.blblblbl.myapplication.data.data_classes.responses.saved.comments.SavedCommentsResponse
import com.blblblbl.myapplication.data.data_classes.responses.saved.link.SavedLinksResponse
import com.blblblbl.myapplication.data.data_classes.responses.subreddit.SubredditsResponse
import com.blblblbl.myapplication.data.data_classes.responses.user_info.UserInfoResponse
import com.blblblbl.myapplication.data.persistent_storage.PersistentStorage
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
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
        val postsApi:PostsApi = retrofit.create(
            PostsApi::class.java
        )
        val userApi:UserApi = retrofit.create(
            UserApi::class.java
        )
        val savedThings: SavedThings = retrofit.create(
            SavedThings::class.java
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
            @GET("comments/{post}")
            suspend fun getPostComments(@Path("post") post:String,@Query("limit") limit:Int, @Header("Authorization") authHeader:String):String
            @GET("/subreddits/search")
            suspend fun searchSubreddits(@Query("count") count:Int,@Query("limit") limit:Int,@Query("q") q:String, @Header("Authorization") authHeader:String):SubredditsResponse
        }
        interface PostsApi{
            @GET("new")
            suspend fun getNewPosts(@Query("after")page:String,@Header("Authorization") authHeader:String):SubredditPostsResponse
            @GET("r/popular")
            suspend fun getPopularPosts(@Query("after")page:String,@Header("Authorization") authHeader:String):SubredditPostsResponse
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
        interface SavedThings{
            @GET("user/{username}/saved?type=links")
            suspend fun getSavedPosts(@Path("username") username:String,@Query("after")page:String,@Header("Authorization") authHeader:String):SavedLinksResponse
            @GET("user/{username}/saved?type=comments")
            suspend fun getSavedComments(@Path("username") username:String,@Query("after")page:String,@Header("Authorization") authHeader:String): String
            @POST("api/save")
            suspend fun saveThing(@Query("category") category: String, @Query("id") id:String, @Header("Authorization") authHeader:String)
            @POST("api/unsave")
            suspend fun unsaveThing(@Query("id") id:String, @Header("Authorization") authHeader:String)
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
    suspend fun getNewPosts(page:String):SubredditPostsResponse{
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        val response = RetrofitServices.postsApi.getNewPosts(page,"bearer $token")
        Log.d("MyLog","getNewPosts response:" + response)
        return response
    }
    suspend fun getPopularPosts(page:String):SubredditPostsResponse{
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        Log.d("MyLog","RepositoryApi getPopularPosts")
        val response = RetrofitServices.postsApi.getPopularPosts(page,"bearer $token")
        Log.d("MyLog", "getPopularPosts response:$response")
        return response
    }
    suspend fun getPostComments(post:String):PostCommentsResponse{
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        val response = RetrofitServices.subredditsApi.getPostComments(post, 5,"bearer $token")
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(PostCommentsResponse::class.java, PostCommentsResponseDeserializer())
        val gson = gsonBuilder.create()
        val correctResponse = response.replace("\"replies\": \"\"","\"replies\": null")
        val classResponse = gson.fromJson(correctResponse, PostCommentsResponse::class.java)
        Log.d("MyLog","search response:" + response)
        return classResponse
    }
    suspend fun searchSubreddits(count: Int,limit: Int,search:String){
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        Log.d("MyLog","search response:" + RetrofitServices.subredditsApi.searchSubreddits(count, limit ,search, "bearer $token"))
    }
    suspend fun meInfo():MeResponse{
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        val response = RetrofitServices.userApi.me("bearer $token")
        Log.d("MyLog", "me response:" + response)
        return response
    }
    suspend fun userInfo(userName:String):UserInfoResponse{
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        val response = RetrofitServices.userApi.user(userName,"bearer $token")
        Log.d("MyLog", "user response:" + response)
        return response
    }
    suspend fun getFriends():FriendsResponse{
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        val response = RetrofitServices.userApi.friendsList("bearer $token")
        Log.d("MyLog", "user response:" + response)
        return response
    }

    suspend fun addToFriends(userName:String){
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        RetrofitServices.userApi.addToFriends(userName,"{\"name\": \"$userName\"}","bearer $token")
    }
    suspend fun getSavedPosts(userName:String,page:String):SavedLinksResponse{
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        val response = RetrofitServices.savedThings.getSavedPosts(userName,page,"bearer $token")
        Log.d("MyLog", "user response:" + response)
        return response
    }
    suspend fun getSavedComments(userName:String,page:String):SavedCommentsResponse{
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        val response = RetrofitServices.savedThings.getSavedComments(userName,page,"bearer $token")
        val gson = GsonBuilder().setLenient().create()
        val correctResponse = response.replace("\"replies\": \"\"","\"replies\": null")
        Log.d("MyLog", "user response:" + response)
        return gson.fromJson(correctResponse,SavedCommentsResponse::class.java)
    }
    suspend fun saveThing(category:String,id: String){
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        RetrofitServices.savedThings.saveThing(category = category,id = id,"bearer $token")
    }
    suspend fun unsaveThing(id: String){
        val token = persistentStorage.getProperty(PersistentStorage.AUTH_TOKEN)
        RetrofitServices.savedThings.unsaveThing(id = id,"bearer $token")
    }
}