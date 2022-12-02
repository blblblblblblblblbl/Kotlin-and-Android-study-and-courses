package com.example.example

import com.google.gson.annotations.SerializedName


data class Locations (

  @SerializedName("info"    ) var info    : InfoLocations?              = InfoLocations(),
  @SerializedName("results" ) var results : ArrayList<ResultsLocation> = arrayListOf()

)