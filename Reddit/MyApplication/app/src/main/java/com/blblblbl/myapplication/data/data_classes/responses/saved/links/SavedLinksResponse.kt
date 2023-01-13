package com.blblblbl.myapplication.data.data_classes.responses.saved.link

import com.google.gson.annotations.SerializedName


data class SavedLinksResponse (

  @SerializedName("kind" ) var kind : String? = null,
  @SerializedName("data" ) var data : Data?   = Data()

)