package com.market.nsu2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.market.nsu2.databinding.ActivityListBinding
import com.market.nsu2.databinding.ActivityMainBinding

class ListActivity : AppCompatActivity() {
    lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}