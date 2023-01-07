package com.blblblbl.myapplication.data.repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

class RepositoryApi {
    object RetrofitServices{
        private const val BASE_URL= "https://www.reddit.com/api/v1/"
        private val gson = GsonBuilder().setLenient().create()
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val authApi:AuthApi = retrofit.create(
            AuthApi::class.java
        )

        interface AuthApi {
            @POST("access_token")
            suspend fun exchangeCodeForToken()
        }

    }
}