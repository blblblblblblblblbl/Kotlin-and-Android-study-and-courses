package com.blblblbl.myapplication.data

import android.util.Log
import com.example.example.Characters
import com.blblblbl.myapplication.data.data_classes.episode_data_classes.Episode
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

class CharactersRepository @Inject constructor() {
    object RetrofitServices{
        private const val BASE_URL= "https://rickandmortyapi.com/api/"
        private val gson = GsonBuilder().setLenient().create()
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val searchCharactersApi:SearchCharactersApi = retrofit.create(
            SearchCharactersApi::class.java
        )
        val searchEpisodeApi:SearchEpisodeApi = retrofit.create(
            SearchEpisodeApi::class.java
        )
        val searchEpisodesApi:SearchEpisodesApi = retrofit.create(
            SearchEpisodesApi::class.java
        )
        interface SearchCharactersApi{
            @GET("character/")
            suspend fun getCharacters(@Query("page") page:Int): Characters
        }
        interface SearchEpisodeApi{
            @GET("episode/{id}")
            suspend fun getEpisode(@Path("id") id:Int): Episode
        }
        interface SearchEpisodesApi{
            @GET("episode/{ids}")
            suspend fun getEpisodes(@Path("ids") ids:String): List<Episode>?
        }
    }

    suspend fun getCharacters(page: Int): Characters? {
        return RetrofitServices.searchCharactersApi.getCharacters(page)
    }
    suspend fun getEpisode(id: Int): Episode? {
        return RetrofitServices.searchEpisodeApi.getEpisode(id)
    }
    suspend fun getEpisodes(ids: String): List<Episode>? {
        Log.d("MyLog",ids)
        return try {
            RetrofitServices.searchEpisodesApi.getEpisodes(ids)
        } catch (e:Throwable){
            null
        }
    }
}