package com.blblblbl.mvvm1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.blblblbl.mvvm1.databinding.ActivityMainBinding
import com.blblblbl.mvvm1.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.text.observe(this){binding.textview.text = it}
        binding.textview.setOnClickListener{
            onClick()
        }
    }
    private fun onClick()
    {
        viewModel.changePhrase()
    }
}