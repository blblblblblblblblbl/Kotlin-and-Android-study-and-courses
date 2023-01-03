package com.market.myapplication.data.repository

import android.util.Log
import com.google.gson.GsonBuilder
import com.market.myapplication.data.data_classes.api.NewsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import javax.inject.Inject

class RepositoryApi @Inject constructor() {
    object RetrofitServices{
        private const val BASE_URL= "https://newsapi.org/v2/"
        private val gson = GsonBuilder().setLenient().create()
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val newsApi:NewsApi = retrofit.create(
            NewsApi::class.java
        )


        interface NewsApi{
            @GET("top-headlines")
            suspend fun getNews(@Query("page") page:Int,
                                  @Query("pageSize") pageSize:Int=10,
                                  @Query("language") language:String="en",
                                  @Query("category") category:String = "sports",
                                  @Query("apiKey") apiKey:String): NewsResponse
        }


    }

    suspend fun getNewsPage(page: Int):NewsResponse{
        return RetrofitServices.newsApi.getNews(page = page, apiKey = API_KEY)
    }
    companion object{
        const val API_KEY:String = "974a1f75dff6491aa3bdcc8750fbf6f4"
    }
}