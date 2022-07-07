package com.example.magicball

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.hardware.SensorManager
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.widget.TextView
import kotlin.random.Random

//import com.example.magicball.ShakeDetector
//import kotlinx.android.synthetic.main.activity_main.*
//import kotlin.math.log

class MainActivity : AppCompatActivity(),ShakeDetector.Listener {
    lateinit var textView: TextView;
    protected var fadeIn = AlphaAnimation(0.0f,1.0f);
    protected var fadeOut = AlphaAnimation(1.0f,0.0f);
    var Answers = arrayOf<String>(
        "It is certain","It is decidedly so","Without a doubt",
        "Yes — definitely","You may rely on it","As I see it, yes","Most likely","Outlook good",
        "Signs point to yes","Yes","Reply hazy, try again","Ask again later","Better not tell you now",
        "Cannot predict now","Concentrate and ask again","Don’t count on it","My reply is no",
        "My sources say no","Outlook not so good", "Very doubtful")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main)

        textView = findViewById<TextView>(R.id.MainTextView);
        fadeIn.duration = 1000;
        fadeOut.duration = 8000;


        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager;
        val sd = ShakeDetector(this);
        sd.start(sensorManager);
    }

    override fun hearShake()
    {
        Log.d("MyLog","shaking");
        textView.startAnimation(fadeOut);
        textView.text = Answers[Random.nextInt(0,20)];
        textView.startAnimation(fadeIn);
    }
}