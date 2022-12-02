package com.blblblbl.myapplication.data

import com.blblblbl.myapplication.entity.UsefulActivity
import com.google.gson.annotations.SerializedName

data class UsefulActivityDto(
    @SerializedName("activity") override var activity: String? = null,
    @SerializedName("type") override var type: String? = null,
    @SerializedName("participants") override var participants: Int? = null,
    @SerializedName("price") override var price: Double? = null,
    @SerializedName("link") override var link: String? = null,
    @SerializedName("key") override var key: String? = null,
    @SerializedName("accessibility") override var accessibility: Double? = null

) : UsefulActivity {
}