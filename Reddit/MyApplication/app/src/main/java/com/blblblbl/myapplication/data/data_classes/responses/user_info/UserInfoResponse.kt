package com.blblblbl.myapplication.data.data_classes.responses.user_info

import com.google.gson.annotations.SerializedName


data class UserInfoResponse (

  @SerializedName("kind" ) var kind : String? = null,
  @SerializedName("data" ) var data : UserInfo?   = UserInfo()

)