package com.blblblbl.pomodoromvvm.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.blblblbl.pomodoromvvm.R
import com.blblblbl.pomodoromvvm.databinding.ActivityMainBinding
import com.blblblbl.pomodoromvvm.model.MyModel
import com.blblblbl.pomodoromvvm.model.TimerService
import com.blblblbl.pomodoromvvm.viewmodel.MyViewModel
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val viewModel: MyViewModel by viewModels()
    val model:MyModel = MyModel(this)
    private lateinit var serviceIntent: Intent
    private var curTime:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.time.observe(this){binding.textView.text=it.toString()}
        binding.bstart.setOnClickListener{
            startOnClick()
        }
        serviceInit()
    }

    private fun serviceInit()
    {
        serviceIntent = Intent(applicationContext, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATE))
    }
    private val updateTime: BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            curTime = intent!!.getDoubleExtra(TimerService.TIME_EXTRA,0.0)
            binding.textView.text = curTime.toString()
        }
    }

    fun startOnClick()
    {
        //startService(serviceIntent)
        viewModel.startTimer()
        model.serviceInit()
        model.startTimer()
        binding.textView.text = curTime.toString()
        //viewModel.startTimer()
    }
}