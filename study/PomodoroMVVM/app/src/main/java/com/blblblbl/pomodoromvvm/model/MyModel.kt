package com.blblblbl.pomodoromvvm.model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blblblbl.pomodoromvvm.databinding.ActivityMainBinding

class MyModel(val context: Context):AppCompatActivity() {
    private lateinit var serviceIntent: Intent
    private var curTime:Double = 0.0
    //private val context = context


    fun serviceInit()
    {

        serviceIntent = Intent(context/*applicationContext*/, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATE))
    }
    private val updateTime: BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            curTime = intent!!.getDoubleExtra(TimerService.TIME_EXTRA,0.0)
        }
    }
    fun startTimer() {
        startService(serviceIntent)
    }
}