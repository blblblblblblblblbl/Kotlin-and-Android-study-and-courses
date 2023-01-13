package com.blblblbl.myapplication.data.data_classes.responses.saved.comments

import com.google.gson.annotations.SerializedName


data class SavedCommentsResponse (

  @SerializedName("kind" ) var kind : String? = null,
  @SerializedName("data" ) var data : Data?   = Data()

)