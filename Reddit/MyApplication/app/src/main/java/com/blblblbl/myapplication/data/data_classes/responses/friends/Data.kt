package com.blblblbl.myapplication.data.data_classes.responses.friends

import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("children" ) var friends : ArrayList<Friend> = arrayListOf()

)