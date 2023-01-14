package com.blblblbl.myapplication.data.data_classes.responses.posts.comments

import com.google.gson.annotations.SerializedName


data class SecureMediaEmbed (
    @SerializedName("approved_at_utc"               ) var approvedAtUtc              : String?           = null

)