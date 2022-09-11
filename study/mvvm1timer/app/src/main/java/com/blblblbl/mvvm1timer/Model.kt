package com.blblblbl.mvvm1timer

import java.util.*

class Model(private val dataSource: DataSource) {

    private var timer:Timer? = null
    private var count:Int = -1
    private var textCallBack:TextCallBack? = null


    fun start(callBack: TextCallBack){
        textCallBack = callBack
        timer = Timer()
        if(count<0){count = dataSource.getInt(CacheDataSource.COUNT_KEY)}
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
        dataSource.saveInt(CacheDataSource.COUNT_KEY,count)
        timer?.cancel()
        timer = null
    }
}