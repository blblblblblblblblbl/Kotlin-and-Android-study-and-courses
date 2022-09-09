package com.blblblbl.mvvm1timer

import java.util.*

class Model() {

    private var timer:Timer? = null
    private var count:Int = 0
    private var textCallBack:TextCallBack? = null


    fun start(callBack: TextCallBack){
        textCallBack = callBack
        timer = Timer()
        val timerTask = object :TimerTask()
        {
            override fun run() {
                count++
                textCallBack?.updateText(count.toString())
            }
        }
        timer?.scheduleAtFixedRate(timerTask,0,1000)

    }
    fun stop(){
        timer?.cancel()
        timer = null
    }
}