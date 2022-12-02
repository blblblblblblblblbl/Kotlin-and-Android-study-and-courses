package com.blblblbl.myapplication.data

import com.blblblbl.myapplication.data.data_classes.PhotoDto
import com.blblblbl.myapplication.data.data_classes.Photos
import com.blblblbl.myapplication.entity.Photo
import javax.inject.Inject

class PhotosRepository @Inject constructor() {
    val dataSource:DataSource = DataSourceImpRetrofit()
    suspend fun getPhoto(): Photos {
        return  dataSource.getPhoto()
    }
}