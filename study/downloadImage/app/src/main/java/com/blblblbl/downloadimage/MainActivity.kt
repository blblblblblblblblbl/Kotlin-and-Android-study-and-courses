package com.blblblbl.downloadimage

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.blblblbl.downloadimage.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.net.URL

class MainActivity : AppCompatActivity() {

    private companion object{
        const val URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/84/LetterS.svg/1200px-LetterS.svg.png"
    }

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        downloadPicGlide(URL)
    }
    fun downloadPic(url:String) {
        val image = binding.imageView

        val callBack = object : ImageCallBack{
            override fun success(bitmap: Bitmap) {
                image.setImageBitmap(bitmap)
            }

            override fun failed() {
                //Toast.makeText(applicationContext,"failed",Toast.LENGTH_SHORT).show()
                Snackbar.make(image,"failed",Snackbar.LENGTH_SHORT).show()
            }
        }
        val netImage = NetImage(url,callBack)
        netImage.start()
    }
    fun downloadPicPicasso(url: String){
        Picasso.get().load(url)
            .transform(CropCircleTransformation())
            .centerCrop()
            .resize(720,1280)
            .placeholder(android.R.drawable.ic_media_pause)
            .error(android.R.drawable.ic_dialog_alert)
            .into(binding.imageView)
    }
    fun downloadPicGlide(url:String){
        Glide.with(this).load(url).transform(CropCircleWithBorderTransformation()).into(binding.imageView)
    }
}