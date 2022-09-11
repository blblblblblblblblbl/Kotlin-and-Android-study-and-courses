package com.blblblbl.mvvm1timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blblblbl.mvvm1timer.databinding.ActivityMainBinding
import com.blblblbl.unittest.Main

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = (application as MyApplication).viewModel
        val textObservable = TextObservable()
        val textView = binding.textTimer
        val textCallBack = object:TextCallBack{
            override fun updateText(str: String)=runOnUiThread {
                textView.text = str
            }
        }
        textObservable.observe(textCallBack)
        viewModel.init(textObservable)
        Main.main(arrayOf("",""))
        //viewModel.init()
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        viewModel.resumeCounting()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopCounting()
    }
}