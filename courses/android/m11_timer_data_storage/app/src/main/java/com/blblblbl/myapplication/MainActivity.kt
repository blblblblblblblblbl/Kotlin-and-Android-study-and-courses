package com.blblblbl.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blblblbl.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var repository:Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repository = RepositoryImpl(this)
        binding.apply {
            tvMain.text = repository.getText()
            bSave.setOnClickListener {
                val text = textEditMain.text.toString()
                repository.saveText(text)
                tvMain.text = repository.getText()
            }
            bClear.setOnClickListener {
                repository.clearText()
                tvMain.text = null
            }
        }
    }
}