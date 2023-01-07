package com.blblblbl.myapplication

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.blblblbl.myapplication.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val redirectUri: Uri? = intent.data
        Log.d("MyLog",redirectUri.toString())
        if (redirectUri != null) {
            viewModel.exchangeCodeForToken(redirectUri)
        }
        setContentView(R.layout.activity_main)
    }
}