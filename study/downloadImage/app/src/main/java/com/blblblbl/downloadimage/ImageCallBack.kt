package com.blblblbl.downloadimage

import android.graphics.Bitmap

interface ImageCallBack {
    fun success(bitmap: Bitmap)
    fun failed()
}