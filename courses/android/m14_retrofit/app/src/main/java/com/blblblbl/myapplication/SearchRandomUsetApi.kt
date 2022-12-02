package com.blblblbl.myapplication

import com.blblblbl.myapplication.data_classes.RandomUserJson2KtKotlin
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers


object RetrofitServices{
    private const val BASE_URL= "https://randomuser.me/api/"
    private val gson = GsonBuilder().setLenient().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    val searchRandomUserApi:SearchRandomUserApi = retrofit.create(
        SearchRandomUserApi::class.java
    )
    interface SearchRandomUserApi{
        /*@Headers(
            "results: application/json",
            "Content-type: application/json"
        )*/
        @GET(" ")
        suspend fun getRandomUser(): RandomUserJson2KtKotlin
    }
}