package com.recycleviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.recycleviewer.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    lateinit var  binding: ActivityEditBinding
    var index = 1
    var imgList = arrayOf(
        R.drawable.plant_img1,
        R.drawable.plant_img2,
        R.drawable.plant_img3,
        R.drawable.plant_img4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun onNextClick(view: View)
    {
        if (index==4) index=0
        binding.imageView2.setImageResource(imgList[index++])
        //Log.d("MyLog","${index}")
    }
    fun onDoneClick(view:View)
    {
        /*intent.putExtra("img",imgList[index-1])
        Log.d("MyLog",imgList[index-1].toString())
        intent.putExtra("title",binding.edTitle.text.toString())
        Log.d("MyLog",binding.edTitle.text.toString())
        intent.putExtra("desc",binding.edDesc.text.toString())
        Log.d("MyLog",binding.edDesc.text.toString())*/
        intent.putExtra("plant",Plant(imgList[index-1],binding.edTitle.text.toString(),binding.edDesc.text.toString()))
        setResult(RESULT_OK,intent)
        finish()
    }
}