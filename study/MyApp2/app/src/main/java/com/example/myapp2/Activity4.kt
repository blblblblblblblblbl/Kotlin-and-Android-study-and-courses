package com.example.myapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.myapp2.databinding.Activity4Binding

class Activity4 : AppCompatActivity() {
    lateinit var binding:Activity4Binding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity4Binding.inflate(layoutInflater);
        setContentView(binding.root)
        binding.imageView.setImageResource(R.drawable.user);
    }
    fun Onclick(view: View)
    {
        if((binding.editName.text.toString() == Constance.masterName)&&(binding.editPassword.text.toString() == Constance.masterPassword))
        {
            binding.textView2.text = "${getString(R.string.hello)} ${Constance.masterName}!"
            binding.imageView.setImageResource(R.drawable.master);
        }
        else if((binding.editName.text.toString() == Constance.hamsterName)&&(binding.editPassword.text.toString() == Constance.hamsterPassword))
        {
            binding.textView2.text = "${getString(R.string.hello)} ${Constance.hamsterName}!"
            binding.imageView.setImageResource(R.drawable.hamster);
        }
        else
        {
            binding.textView2.text = "${getString(R.string.fuck_you)} ${binding.editName.text}"
            binding.imageView.setImageResource(R.drawable.fuck_you);
        }
    }
    object Constance
    {
        const val masterName:String = "master";
        const val hamsterName:String = "hamster";
        const val masterPassword:String = "pass";
        const val hamsterPassword:String = "pass";
    }
}