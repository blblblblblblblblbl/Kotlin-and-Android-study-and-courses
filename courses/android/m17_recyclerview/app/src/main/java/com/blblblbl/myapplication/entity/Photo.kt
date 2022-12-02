package com.blblblbl.myapplication.entity

import com.blblblbl.myapplication.data.data_classes.Camera
import com.blblblbl.myapplication.data.data_classes.Rover

interface Photo {
    var id        : Int?
    var sol       : Int?
    var camera    : Camera?
    var imgSrc    : String?
    var earthDate : String?
    var rover     : Rover?
}