package com.blblblbl.myapplication.data.data_classes

import com.google.gson.annotations.SerializedName


data class Photos (

  @SerializedName("photos" ) var photoDtos : ArrayList<PhotoDto> = arrayListOf()

)