package com.example.myapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    var a = 0; var b = 6;
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MyLog","onCreate");
    }

    override fun onStart()
    {
        super.onStart()
        Log.d("MyLog","onStart");
    }

    override fun onResume()
    {
        super.onResume()
        Log.d("MyLog","onResume");
    }

    override fun onPause()
    {
        super.onPause()
        Log.d("MyLog","onPause");
        Log.d("MyLog","Paused "+(++a).toString()+" times");
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MyLog","onRestart");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLog","onDestroy");
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLog","onStop");
    }
}