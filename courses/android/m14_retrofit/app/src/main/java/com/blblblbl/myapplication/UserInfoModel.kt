package com.blblblbl.myapplication

import com.google.gson.annotations.SerializedName

data class UserInfoModel(
    @SerializedName("results") val results:ResultsModel,
    @SerializedName("info") val info:InfoModel,

)
data class ResultsModel(
    @SerializedName("gender") val gender:String,
    @SerializedName("name") val name:String,
    @SerializedName("location") val location:String,
    @SerializedName("email") val email:String,
    @SerializedName("login") val login:String,
    @SerializedName("phone") val phone:String,
    @SerializedName("cell") val cell:String,
    @SerializedName("id") val id:String,
    @SerializedName("picture") val picture:String,
    @SerializedName("nat") val nat:String,
)
data class InfoModel(
    @SerializedName("seed") val seed:String,
    @SerializedName("results") val results:String,
    @SerializedName("page") val page:String,
    @SerializedName("version") val version:String,
)