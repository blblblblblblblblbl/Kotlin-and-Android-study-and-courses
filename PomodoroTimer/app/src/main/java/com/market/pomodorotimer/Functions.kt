package com.market.pomodorotimer

import kotlin.math.roundToInt

class Functions {
    companion object{
        fun getTimeStringFromDouble(time:Double):String
        {
            val resultInt = Math.abs(time.roundToInt())
            val hours = resultInt%86400/3600
            val minutes = resultInt%86400%3600/60
            val seconds = resultInt%86400%3600%60
            if (hours == 0){
                if (minutes == 0) return makeTimeString(seconds)
                else return makeTimeString(minutes,seconds)
            }
            return makeTimeString(hours,minutes,seconds)
        }
        fun makeTimeString(hour:Int,min:Int,sec:Int):String = String.format("%02d:%02d:%02d",hour,min,sec)
        fun makeTimeString(min:Int,sec:Int):String = String.format("%02d:%02d",min,sec)
        fun makeTimeString(sec:Int):String = String.format("%02d",sec)
    }
}