package com.blblblbl.mvvm1timer

import java.util.*

class Model(private val dataSource: DataSource) {

    companion object{
        private const val KEY_COUNTER = "counterKey"
    }

    private var timer:Timer? = null
    private var count:Int = -1
    private var textCallBack:TextCallBack? = null
    private val timerTask get() = object :TimerTask()
    {
        override fun run() {
            count++
            textCallBack?.updateText(count.toString())
        }
    }


    fun start(callBack: TextCallBack){
        textCallBack = callBack
        timer = Timer()
        if(count<0){count = dataSource.getInt(CacheDataSource.COUNT_KEY)}
        timer?.scheduleAtFixedRate(timerTask,0,1000)

    }
    fun stop(){
        dataSource.saveInt(CacheDataSource.COUNT_KEY,count)
        timer?.cancel()
        timer = null
    }

    private class TestCallBack:TextCallBack{
        var text = ""
        override fun updateText(str: String) {
            text = str
        }
    }

    private class TestDataSource:DataSource{
        private var int:Int = Int.MIN_VALUE
        override fun saveInt(key: String, value: Int) {
            int = value
        }
        override fun getInt(key: String) = int
    }

}