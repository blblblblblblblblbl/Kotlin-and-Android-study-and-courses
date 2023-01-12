package com.blblblbl.myapplication.data.data_classes.responses.subreddit

import com.google.gson.annotations.SerializedName


data class SubredditsResponse (

  @SerializedName("kind" ) var kind : String? = null,
  @SerializedName("data" ) var data : Data?   = Data()

)