package com.blblblbl.myapplication.data

import com.blblblbl.myapplication.data.data_classes.Photos
import com.blblblbl.myapplication.entity.Photo

interface DataSource {
    suspend fun getPhoto(): Photos
}