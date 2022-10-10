package com.market.nsu2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.market.nsu2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bToListActivity.setOnClickListener { bOnClick() }
    }
    fun bOnClick(){
        Log.d("MyLog","clicked")
        startActivity(Intent(this, ListActivity::class.java))
    }
}