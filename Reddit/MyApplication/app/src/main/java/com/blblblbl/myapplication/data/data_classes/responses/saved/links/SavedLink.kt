package com.blblblbl.myapplication.data.data_classes.responses.saved.link

import com.blblblbl.myapplication.data.data_classes.responses.saved.links.LinkData
import com.google.gson.annotations.SerializedName


data class SavedLink (

  @SerializedName("kind" ) var kind : String? = null,
  @SerializedName("data" ) var linkData : LinkData?   = LinkData()

)