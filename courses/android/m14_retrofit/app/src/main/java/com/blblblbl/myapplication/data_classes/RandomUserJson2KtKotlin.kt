package com.blblblbl.myapplication.data_classes

import com.google.gson.annotations.SerializedName


data class RandomUserJson2KtKotlin(

    @SerializedName("results") var results: ArrayList<Results> = arrayListOf(),
    @SerializedName("info") var info: Info? = Info()

)