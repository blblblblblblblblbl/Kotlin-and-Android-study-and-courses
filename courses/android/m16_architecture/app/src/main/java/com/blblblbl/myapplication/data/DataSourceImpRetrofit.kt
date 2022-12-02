package com.blblblbl.myapplication.data

import com.blblblbl.myapplication.entity.UsefulActivity
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

class DataSourceImpRetrofit @Inject constructor():DataSource {
    object RetrofitServices{
        private const val BASE_URL= "https://www.boredapi.com/api/"
        private val gson = GsonBuilder().setLenient().create()
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val searchActivityApi:SearchActivityApi = retrofit.create(
            SearchActivityApi::class.java
        )
        interface SearchActivityApi{
            @GET("activity/")
            suspend fun getActivity(): UsefulActivityDto
        }
    }

    override suspend fun getActivity(): UsefulActivity {
        return RetrofitServices.searchActivityApi.getActivity()
    }
}