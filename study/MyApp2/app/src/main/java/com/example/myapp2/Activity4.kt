package com.example.myapp2

import android.content.Intent
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
       /* if((binding.editName.text.toString() == Info.masterName)&&(binding.editPassword.text.toString() == Info.masterPassword))
        {
            binding.textView2.text = "${getString(R.string.hello)} ${Info.masterName}!"
            binding.imageView.setImageResource(R.drawable.master);
        }
        else if((binding.editName.text.toString() == Info.hamsterName)&&(binding.editPassword.text.toString() == Info.hamsterPassword))
        {
            binding.textView2.text = "${getString(R.string.hello)} ${Info.hamsterName}!"
            binding.imageView.setImageResource(R.drawable.hamster);
        }
        else
        {
            binding.textView2.text = "${getString(R.string.fuck_you)} ${binding.editName.text}"
            binding.imageView.setImageResource(R.drawable.fuck_you);
        }*/
        for(el in Info.persons)
        {
            val name = binding.editName.text.toString();
            val pass = binding.editPassword.text.toString()
            if (el.name==name)
            {
                if (el.pass == pass)
                {
                    binding.textView2.text = "${getString(R.string.hello)} ${el.name}!"
                    binding.imageView.setImageResource(this.resources.getIdentifier(el.image,"drawable",this.packageName))
                }
                else
                {
                    binding.textView2.text = "${getString(R.string.fuck_you)} ${binding.editName.text}"
                    binding.imageView.setImageResource(R.drawable.fuck_you);
                }
                return
            }
        }
    }
    fun OnRegister (view: View)
    {
        var intent = Intent(this,registerActivity::class.java);
        startActivity(intent);
    }

}