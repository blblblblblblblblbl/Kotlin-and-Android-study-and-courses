package com.recycleviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.recycleviewer.databinding.ActivityViewBinding

class ViewActivity : AppCompatActivity() {
    lateinit var binding:ActivityViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    fun init()
    {
        var plant = intent.getSerializableExtra("plant") as Plant
        binding.viewImg.setImageResource(plant.imageId)
        binding.viewTitleText.text = plant.title
        binding.viewDescText.text = plant.desc
    }
    fun BackClick(view: View)
    {
        finish()
    }
}