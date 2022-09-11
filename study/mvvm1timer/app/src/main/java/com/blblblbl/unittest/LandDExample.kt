package com.blblblbl.unittest

import android.util.Log

class LandDExample {
}


class Doer(private val logger:Logging) {
    private var mainThingDone = false
    fun doMain(){
        if(!mainThingDone){
            logger.log("main thing done")
            mainThingDone = true
        }
    }
}


class LoggingTool():Logging{
    override fun log(message: String) {
        Log.d(javaClass.canonicalName,message)
    }
}

interface Logging{
    fun log(message:String)
}