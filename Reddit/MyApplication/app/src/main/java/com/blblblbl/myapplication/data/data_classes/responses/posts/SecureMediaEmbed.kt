package com.blblblbl.myapplication.data.data_classes.responses.posts

import com.google.gson.annotations.SerializedName


data class SecureMediaEmbed (
    @SerializedName("after"      ) var after     : String?             = null,
)