package com.blblblbl.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.blblblbl.myapplication.data.data_classes.PhotoDto
import com.blblblbl.myapplication.data.data_classes.Photos
import com.blblblbl.myapplication.databinding.ActivityMainBinding
import com.blblblbl.myapplication.presentation.MainViewModel
import com.bumptech.glide.Glide
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
       /* binding.bRefresh.setOnClickListener {
            lifecycleScope.launch {
                viewModel.reloadPhotos()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.photos.collect{photos->
                if (photos!=null){
                    val adapter = CustomAdapter(photos) {photo-> onItemClick(photo) }
                    binding.rvMain.adapter = adapter

                }
            }
        }*/
        setContentView(binding.root)
    }
    /*private fun onItemClick(item:PhotoDto){
        Log.d("MyLog","${item.imgSrc.toString()}")
    }
    private fun photosToscreen(photos: Photos?) {
        binding.tvMain.text = photos?.photoDtos?.joinToString { "," } ?: "blblbl"
    }*/
}