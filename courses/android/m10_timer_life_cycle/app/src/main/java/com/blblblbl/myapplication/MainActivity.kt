package com.blblblbl.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.blblblbl.myapplication.databinding.ActivityMainBinding
import com.google.android.material.slider.Slider
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var isStarted = false
    private var timerTask = 10
    private var currentTime = 0
    private lateinit var job:Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainSlider.addOnChangeListener(object : Slider.OnChangeListener {
            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                timerTask = value.toInt()
                binding.tvTimer.text = timerTask.toString()
            }
        })
        binding.bStart.setOnClickListener {
            isStarted = !isStarted
            startOrStopTimer()
            binding.mainSlider.isEnabled = !isStarted
        }
        savedInstanceState?.let { bundle: Bundle ->
            timerTask = bundle.getInt(TIMERTASK)
            currentTime = bundle.getInt(CURRENTTIME)
            isStarted = bundle.getBoolean(ISSTARTED)
            Log.d("myLog",timerTask.toString())
            Log.d("myLog",currentTime.toString())
            Log.d("myLog",isStarted.toString())
            startTimer(currentTime)
            binding.mainSlider.isEnabled = !isStarted
        }
    }

    override fun onDestroy() {
        Log.d("myLog","onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        job.cancel()
        Log.d("myLog",timerTask.toString())
        Log.d("myLog",currentTime.toString())
        Log.d("myLog",isStarted.toString())
        outState.putInt(TIMERTASK, timerTask)
        outState.putInt(CURRENTTIME,currentTime)
        outState.putBoolean(ISSTARTED,isStarted)
        super.onSaveInstanceState(outState)
    }

    private fun startOrStopTimer(){
        if (isStarted) {
            startTimer(timerTask)
        }
        else {
            stopTimer()
        }
    }
    private fun startTimer(fromTime:Int){
        binding.bStart.text = getString(R.string.b_stop)
        job = GlobalScope.launch {
            timer(fromTime)
        }
    }
    private fun stopTimer(){
        binding.bStart.text = getString(R.string.b_start)
        job.cancel()
        binding.progress.progress = 100
        binding.tvTimer.text = timerTask.toString()
    }
    suspend fun timer(fromTime:Int)= coroutineScope{
        launch(Dispatchers.Main) {
            val time = fromTime
            for (i in time downTo 0){
                currentTime = i
                binding.tvTimer.text = i.toString()
                binding.progress.progress = (i.toDouble()/timerTask*100).toInt()
                delay(1000)

            }
        }
    }
    companion object{
        const val ISSTARTED = "is_started_key"
        const val TIMERTASK = "timer_task_key"
        const val CURRENTTIME ="current_time"
    }
}