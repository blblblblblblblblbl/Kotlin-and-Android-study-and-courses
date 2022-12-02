package com.blblblbl.myapplication.data_classes

import com.google.gson.annotations.SerializedName


data class Dob(

    @SerializedName("date") var date: String? = null,
    @SerializedName("age") var age: Int? = null

)