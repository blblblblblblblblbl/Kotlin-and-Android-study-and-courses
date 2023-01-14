package com.blblblbl.myapplication.data.data_classes.responses.posts.comments

import com.google.gson.annotations.SerializedName


data class Source (

  @SerializedName("url"    ) var url    : String? = null,
  @SerializedName("width"  ) var width  : Int?    = null,
  @SerializedName("height" ) var height : Int?    = null

)