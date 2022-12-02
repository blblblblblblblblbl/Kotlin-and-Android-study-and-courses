package com.blblblbl.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blblblbl.myapplication.databinding.ActivityMainBinding
import com.google.android.material.slider.Slider
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var isStarted = false
    private var timerTask = 10
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
    }
    private fun startOrStopTimer(){
        if (isStarted) {
            startTimer()
        }
        else {
            stopTimer()
        }
    }
    private fun startTimer(){
        binding.bStart.text = getString(R.string.b_stop)
        job = GlobalScope.launch {
            timer()
        }
    }
    private fun stopTimer(){
        binding.bStart.text = getString(R.string.b_start)
        job.cancel()
        binding.progress.progress = 100
        binding.tvTimer.text = timerTask.toString()
    }
    suspend fun timer()= coroutineScope{
        launch(Dispatchers.Main) {
            val time = timerTask
            for (i in time downTo 0){
                binding.tvTimer.text = i.toString()
                binding.progress.progress = (i.toDouble()/time*100).toInt()
                delay(1000)

            }
        }
    }
}