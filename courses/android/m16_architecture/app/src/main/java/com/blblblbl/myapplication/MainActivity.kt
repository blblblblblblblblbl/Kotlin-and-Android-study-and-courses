package com.blblblbl.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.blblblbl.myapplication.databinding.ActivityMainBinding
import com.blblblbl.myapplication.entity.UsefulActivity
import com.blblblbl.myapplication.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.bRefresh.setOnClickListener {
            lifecycleScope.launch {
                viewModel.reloadState()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.activity.collect{activity->
                activityToscreen(activity)
            }
        }
        setContentView(binding.root)
    }

    private fun activityToscreen(activity: UsefulActivity?) {
        binding.tvMain.text = activity?.activity ?: "blblbl"
    }
}