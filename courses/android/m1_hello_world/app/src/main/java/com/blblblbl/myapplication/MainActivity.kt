package com.blblblbl.myapplication

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.blblblbl.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            bReset.visibility = View.INVISIBLE
            tvMain.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.green))
            tvMain.text = getString(R.string.all_places_free)
            bRemove.isEnabled = false
            bRemove.setOnClickListener {
                setCounter(counter.dec())
            }
            bAdd.setOnClickListener {
                setCounter(counter.inc())
            }
            bReset.setOnClickListener {
                setCounter(49)
                setCounter(0)
            }
        }
    }
    private fun setCounter(count: Int) {
        if (count < MIN_PLACES || count > MAX_PLACES) return
        counter = count
        binding.tvCounter.text = counter.toString()
        when (counter){
            MIN_PLACES->{
                binding.tvMain.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.green))
                binding.tvMain.text = getString(R.string.all_places_free)
                binding.bRemove.isEnabled = false
            }
            MAX_PLACES->{
                binding.bReset.visibility = View.VISIBLE
                binding.tvMain.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.red))
                binding.tvMain.text = getString(R.string.no_places)
                binding.bAdd.isEnabled = false
            }
            else->{
                binding.bReset.visibility = View.INVISIBLE
                binding.tvMain.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.blue))
                binding.tvMain.text = "${getString(R.string.places_left)}: ${MAX_PLACES-counter}"
                binding.bAdd.isEnabled = true
                binding.bRemove.isEnabled = true
            }
        }
    }
    companion object{
        const val MAX_PLACES:Int = 50
        const val MIN_PLACES:Int = 0
    }
}