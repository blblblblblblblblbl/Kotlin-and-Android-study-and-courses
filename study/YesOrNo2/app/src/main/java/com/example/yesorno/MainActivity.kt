package com.example.yesorno

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    var Answers = arrayOf<String>("Yes","No");
    protected var fadeIn = AlphaAnimation(0.0f, 1.0f)
    protected var fadeOut = AlphaAnimation(1.0f, 0.0f)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main)
        textView = findViewById<TextView>(R.id.MainText)
        fadeIn.duration=1000;
        fadeOut.duration=1000;
    }
    fun OnClick(view: View)
    {
        var index:Int = Random.nextInt(0,2);
        textView.startAnimation(fadeOut);
        textView.text = Answers[index];
        textView.startAnimation(fadeIn);
    }

}