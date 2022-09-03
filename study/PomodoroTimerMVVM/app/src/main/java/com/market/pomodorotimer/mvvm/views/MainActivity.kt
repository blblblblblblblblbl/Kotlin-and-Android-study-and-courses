package com.market.pomodorotimer.mvvm.views

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.market.pomodorotimer.R
import com.market.pomodorotimer.databinding.ActivityMainBinding
import com.market.pomodorotimer.mvvm.models.TimerService
import com.market.pomodorotimer.mvvm.viewmodels.MainViewModel
import com.market.pomodorotimer.mvvm.viewmodels.SharedCode
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    /*private  var timeStarted = false
    private var curTime:Double = 0.0
    private var workTime:Int = 0
    private var relaxTime:Int = 0
    private lateinit var serviceIntent: Intent
    private var isWorkPhase: Boolean = true
    private lateinit var mp:MediaPlayer*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel.timeWork.observe(this,{binding.timeTV.text = SharedCode.getTimeStringFromDouble(it.toDouble())})
        mainViewModel.start()
        /*onClickInit()
        serviceInit()
        mp = MediaPlayer.create(this, R.raw.ringtone)
        binding.bStopAlarm.visibility = View.GONE
        mainViewModel.timeWork.observe(this,{workTime = it})
        mainViewModel.timeRelax.observe(this,{relaxTime = it})
        configureTimer()*/
    }
    /*private fun serviceInit()
    {
        serviceIntent = Intent(applicationContext, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATE))
    }*/
   /* private val updateTime:BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            curTime = intent!!.getDoubleExtra(TimerService.TIME_EXTRA,0.0)
            if (Math.abs(curTime.roundToInt())==0) timerEnd()
            binding.timeTV.text = SharedCode.getTimeStringFromDouble(curTime)//getTimeStringFromDouble(curTime)
        }
    }*/
    /*fun timerEnd(){
        stopTimer()
        alarm()
    }
    fun alarm()
    {
        Log.d("MyLog","alarm")
        binding.centr.visibility = View.GONE
        binding.placeHolder.visibility =View.GONE
        binding.bStopAlarm.visibility = View.VISIBLE
        mp.start()
    }
    fun stopAlarm(){
        if (mp.isPlaying) {
            mp.stop()
            mp.prepare()
        }
        binding.centr.visibility = View.VISIBLE
        binding.placeHolder.visibility = View.VISIBLE
        binding.bStopAlarm.visibility = View.GONE
        isWorkPhase = !isWorkPhase
    }*/
    /*fun onClickInit()
    {
        binding.apply {
            bPlay.setOnClickListener(){
                startStopTimer()
            }
            bRestart.setOnClickListener() {
                resetTimer()
            }
            bConfig.setOnClickListener(){
                configureTimer()
            }
            bStopAlarm.setOnClickListener()
            {
                stopAlarm()
            }
        }
    }


    private fun startStopTimer()
    {
        if (timeStarted) stopTimer()
        else startTimer()
    }
    private fun startTimer()
    {
        if(Math.abs(curTime.roundToInt())==0){
            if(isWorkPhase) curTime = workTime.toDouble()
            else curTime = relaxTime.toDouble()
        }
        serviceIntent.putExtra(TimerService.TIME_EXTRA,-Math.abs(curTime))
        startService(serviceIntent)
        binding.bPlay.icon = getDrawable(R.drawable.ic_baseline_pause_36)
        timeStarted = true
    }
    private fun stopTimer()
    {
        stopService(serviceIntent)
        binding.bPlay.icon = getDrawable(R.drawable.ic_baseline_play_arrow_36)
        timeStarted = false
    }
    private fun resetTimer()
    {
        stopTimer()
        curTime = 0.0
        binding.timeTV.text = SharedCode.getTimeStringFromDouble(workTime.toDouble())
    }

    private fun configureTimer() {
        supportFragmentManager.beginTransaction().add(binding.placeHolder.id, TimeSelector.newInstance()).commit()
        binding.centr.visibility = View.GONE
    }*/



///////////////////////////////////////////////////////////////////////////////////////////////
    //OLD FUNCTIIONS
    ///////////////////////////////////////////////////////////////////////////////////////////////////



    /*private fun configureTimer() {
       *//* ftrans = supportFragmentManager.beginTransaction()
        if (fIsOpen) {
            ftrans!!.remove(fragConfigure)
        }
        else{
            fragConfigure = TimeSelector.newInstance()
            binding.centr.visibility = View.GONE
            ftrans!!.add(binding.placeHolder.id,fragConfigure)
        }
        fIsOpen = !fIsOpen
        ftrans!!.commit()*//*
        //var timeSelector = TimeSelector.newInstance()
        supportFragmentManager.beginTransaction().add(binding.placeHolder.id,TimeSelector.newInstance()).commit()
        binding.centr.visibility = View.GONE
        *//*dataModel.timeToAct.observe(this,{
            curTime = -it.toDouble()
            binding.timeTV.text = Functions.getTimeStringFromDouble(curTime)
        })*//*
    }*/
    /*private fun openFrag(f:Fragment,idHolder: Int){
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder,f)
            .commit()
    }*/
}