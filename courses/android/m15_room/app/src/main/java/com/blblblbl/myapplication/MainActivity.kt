package com.blblblbl.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.blblblbl.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    //lateinit var wordDao:WordDao
    private val viewModel:MainViewModel by viewModels{
        object :ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val wordDao = (application as MyApplication).db.wordDao()
                return MainViewModel(wordDao) as T
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //wordDao = (application as MyApplication).db.wordDao()
        binding.apply {
            bAdd.setOnClickListener { addWord(tInpEd.text.toString()) }
            bClear.setOnClickListener { viewModel.onClearButton() }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.allWords.collect{ words->
                binding.tvMain.text = words.takeLast(5).joinToString(separator = "\r\n")
            }
        }
    }
    fun addWord(word: String){
        try {
            viewModel.onAddButton(word)
        }
        catch (throwable:Throwable){
            Toast.makeText(applicationContext,throwable.message,Toast.LENGTH_SHORT).show()
        }
    }
}