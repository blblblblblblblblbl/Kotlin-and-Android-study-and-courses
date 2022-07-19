package com.example.myapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapp2.databinding.ActivityRegisterBinding

class registerActivity : AppCompatActivity() {
    lateinit var binding:ActivityRegisterBinding;
    var index:Int = 0;
    var avatarsLength:Int = 4;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater);
        setContentView(binding.root);
        binding.imageView2.setImageResource(this.resources.getIdentifier("avatar${index}","drawable",this.packageName))
    }
    fun onBackClick(view: View)
    {
        finish();
    }
    fun onNextClick(view: View)
    {
        if (index==avatarsLength-1)
        {
            index=0;
        }
        else
        {
            index++;
        }
        setImage("avatar${index}")
    }
    fun onPrevClick(view:View)
    {
        if (index==0)
        {
            index=avatarsLength-1;
        }
        else
        {
            index--;
        }
        setImage("avatar${index}")
    }
    fun onRegisterClick(view: View)
    {
        Info.persons.add(Person(binding.editName2.text.toString(),binding.editPassword2.text.toString(),"avatar${index}"))
        binding.textView3.text = "${binding.editName2.text.toString()} has been registered"
    }
    fun setImage(name:String)
    {
        binding.imageView2.setImageResource(this.resources.getIdentifier(name,"drawable",this.packageName))
    }
}