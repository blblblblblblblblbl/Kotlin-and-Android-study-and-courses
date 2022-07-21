package com.example.myapp2

import android.graphics.drawable.Drawable
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.myapp2.databinding.ActivityAvatarBinding

class AvatarActivity : AppCompatActivity() {
    //lateinit var img:Drawable
    lateinit var binding: ActivityAvatarBinding
    lateinit var str:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAvatarBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun onClickImg(view: View)
    {
        //var imageView:ImageView = ImageView()
        //imageView.drawable.toString()
        //img = (view as ImageView).drawable
        reset()
        (view as ImageView).alpha=0.5f
        str= (view as ImageView).getTag().toString()
        Log.d("MyLog",str)


    }
    private fun reset()
    {
        binding.imageView3.alpha=1f
        binding.imageView4.alpha=1f
        binding.imageView5.alpha=1f
        binding.imageView6.alpha=1f
    }
    fun onClickOk(view: View)
    {
        //intent.putExtra("img",img)
        if (str!=null)
        {
            intent.putExtra("img",str)
            setResult(RESULT_OK,intent)
        }
        else
        {
            setResult(RESULT_CANCELED,intent)
        }
        finish()
    }
}