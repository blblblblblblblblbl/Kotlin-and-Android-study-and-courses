package com.blblblbl.myapplication.data.data_classes.responses.posts.comments

import com.blblblbl.myapplication.data.data_classes.responses.saved.comments.CommentData
import com.google.gson.annotations.SerializedName


data class Comment (

  @SerializedName("kind" ) var kind : String? = null,
  @SerializedName("data" ) var data : CommentData?   = CommentData()

)