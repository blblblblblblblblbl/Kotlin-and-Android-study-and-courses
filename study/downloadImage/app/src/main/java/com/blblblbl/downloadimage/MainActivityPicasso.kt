package com.blblblbl.downloadimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blblblbl.downloadimage.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import java.net.URL

class MainActivityPicasso : AppCompatActivity() {
    private companion object {
        const val URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/84/LetterS.svg/1200px-LetterS.svg.png"
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Picasso.get().load(URL).centerCrop()
            .resize(720,1280)
            .placeholder(android.R.drawable.ic_media_pause)
            .error(android.R.drawable.ic_dialog_alert)
            .into(binding.imageView)
    }
}