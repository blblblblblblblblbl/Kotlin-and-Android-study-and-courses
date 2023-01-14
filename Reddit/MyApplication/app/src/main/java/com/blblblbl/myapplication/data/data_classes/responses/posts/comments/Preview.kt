package com.blblblbl.myapplication.data.data_classes.responses.posts.comments

import com.google.gson.annotations.SerializedName


data class Preview (

  @SerializedName("images"  ) var images  : ArrayList<Images> = arrayListOf(),
  @SerializedName("enabled" ) var enabled : Boolean?          = null

)