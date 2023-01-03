package com.market.myapplication.data.data_classes.custom_json

import com.google.gson.annotations.SerializedName

data class CustomJson(
    @SerializedName("url") var url: String? = null
)
