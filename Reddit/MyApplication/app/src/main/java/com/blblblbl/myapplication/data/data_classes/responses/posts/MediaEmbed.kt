package com.blblblbl.myapplication.data.data_classes.responses.posts

import com.google.gson.annotations.SerializedName


data class MediaEmbed (
    @SerializedName("after"      ) var after     : String?             = null,
)