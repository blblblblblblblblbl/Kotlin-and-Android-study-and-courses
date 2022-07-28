package com.market.pomodorotimer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.market.pomodorotimer.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  var timeStarted = false
    private var time:Double = 0.0
    private lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClickInit()
        serviceInit()
    }
    fun serviceInit()
    {
        serviceIntent = Intent(applicationContext,TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATE))
    }
    private val updateTime:BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            time = intent!!.getDoubleExtra(TimerService.TIME_EXTRA,0.0)
            binding.timeTV.text = getTimeStringFromDouble(time)
        }
    }
    private fun getTimeStringFromDouble(time:Double):String
    {
        val resultInt = time.roundToInt()
        val hours = resultInt%86400/3600
        val minutes = resultInt%86400%3600/60
        val seconds = resultInt%86400%3600%60
        return makeTimeString(hours,minutes,seconds)
    }
    private fun makeTimeString(hour:Int,min:Int,sec:Int):String = String.format("%02d:%02d:%02d",hour,min,sec)
    fun onClickInit()
    {
        binding.apply {
            bPlay.setOnClickListener(){
                startStopTimer(it)
            }
            bRestart.setOnClickListener() {
                resetTimer(it)
            }
        }
    }
    fun startStopTimer(view: View)
    {
        if (timeStarted) stopTimer()
        else startTimer()
    }
    fun startTimer()
    {
        serviceIntent.putExtra(TimerService.TIME_EXTRA,time)
        startService(serviceIntent)
        binding.bPlay.icon = getDrawable(R.drawable.ic_baseline_pause_24)
        timeStarted = true
    }
    fun stopTimer()
    {
        stopService(serviceIntent)
        binding.bPlay.icon = getDrawable(R.drawable.ic_baseline_play_arrow_24)
        timeStarted = false
    }
    fun resetTimer(view: View)
    {
        stopTimer()
        time = 0.0
        binding.timeTV.text = getTimeStringFromDouble(time)
    }
}