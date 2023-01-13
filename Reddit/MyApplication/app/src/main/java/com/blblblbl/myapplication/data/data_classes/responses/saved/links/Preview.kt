package com.blblblbl.myapplication.data.data_classes.responses.saved.link

import com.google.gson.annotations.SerializedName


data class Preview (

  @SerializedName("images"  ) var images  : ArrayList<Images> = arrayListOf(),
  @SerializedName("enabled" ) var enabled : Boolean?          = null

)