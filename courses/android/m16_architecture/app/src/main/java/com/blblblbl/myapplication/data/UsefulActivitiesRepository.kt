package com.blblblbl.myapplication.data

import com.blblblbl.myapplication.entity.UsefulActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject
import javax.inject.Qualifier

class UsefulActivitiesRepository @Inject constructor() {
    /*object RetrofitServices{
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

    suspend fun getUsefulActivity():UsefulActivity{
        return RetrofitServices.searchActivityApi.getActivity()
    }*/
    val dataSource:DataSource = DataSourceImpRetrofit()
    /*lateinit var dataSource: DataSource
    init {
        dataSource = DataSourceModule.bindDataSource()
    }*/
    suspend fun getUsefulActivity():UsefulActivity{
        return dataSource.getActivity()
    }
}
@Qualifier
annotation class ActivityALocationListener
@Module
@InstallIn(ActivityComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindDataSource(
        dataSourceImpRetrofit: DataSourceImpRetrofit
    ): DataSource
}
