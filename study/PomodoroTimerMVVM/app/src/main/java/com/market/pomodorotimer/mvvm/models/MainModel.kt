package com.market.pomodorotimer.mvvm.models

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import com.market.pomodorotimer.mvvm.viewmodels.MainViewModel
import kotlin.math.roundToInt

class MainModel(private val viewModel: MainViewModel): AppCompatActivity()/*Application()*/ {
    private lateinit var serviceIntent: Intent
    var curTime:Double = 99.0
    var timeStarted:Boolean = false

    /*override fun onCreate() {
        super.onCreate()
        serviceInit()
    }*/
    private fun serviceInit() {
        serviceIntent = Intent(  viewModel.getApplication()/*applicationContext*/, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter() /*IntentFilter(TimerService.TIMER_UPDATE)*/)
        //registerReceiver()
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            curTime = intent!!.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            viewModel.updateTime(curTime)
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