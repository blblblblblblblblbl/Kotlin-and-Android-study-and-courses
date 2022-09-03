package com.market.pomodorotimer.mvvm.viewmodels

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import androidx.activity.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.market.pomodorotimer.mvvm.models.MainModel
import com.market.pomodorotimer.mvvm.models.TimerService

class MainViewModel(application: Application): AndroidViewModel(application) {
    val timeToAct:MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val timeWork:MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val timeRelax:MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    var timeStarted = false
    var curTime:Double = 0.0
    var workTime:Int = 0
    var relaxTime:Int = 0
    lateinit var serviceIntent: Intent
    var isWorkPhase: Boolean = true
    lateinit var mp: MediaPlayer

    private var mainModel = MainModel(this)

    fun start(){
        //mainModel.onCreate()
        //mainModel.startTimer()
        startTimer()
    }
    fun updateTime(time:Double)
    {
        curTime = time
    }

    private fun serviceInit() {
        serviceIntent = Intent(  getApplication()/*applicationContext*/, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter() /*IntentFilter(TimerService.TIMER_UPDATE)*/)
        //registerReceiver()
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            curTime = intent!!.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            updateTime(curTime)
            //if (Math.abs(curTime.roundToInt()) == 0) timerEnd()
        }
    }

    fun timerEnd(){
        stopTimer()
    }

    fun stopTimer() {
        stopService(serviceIntent)
        timeStarted = false
    }

    fun startTimer() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA,-Math.abs(curTime))
        startService(serviceIntent)
        timeStarted = true
    }

}