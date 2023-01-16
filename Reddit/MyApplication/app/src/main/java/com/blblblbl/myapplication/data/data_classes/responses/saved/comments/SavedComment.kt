package com.blblblbl.myapplication.data.data_classes.responses.saved.comments

import com.google.gson.annotations.SerializedName


data class SavedComment (

  @SerializedName("kind" ) var kind : String? = null,
  @SerializedName("data" ) var commentData : CommentData?   = CommentData()

)