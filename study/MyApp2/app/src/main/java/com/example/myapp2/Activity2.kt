package com.example.myapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.TextView

class Activity2 : AppCompatActivity() {
    var textsize:Float = 10f;
    lateinit var text:TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        text = findViewById<TextView>(R.id.textView);
        text.textSize=textsize;
    }
    fun OnClick(view:View)
    {
        text.text = "Hello!"
    }
    fun OnClickBye(view:View)
    {
        text.text = "See you)"
    }
    fun OnClickBig(view:View)
    {
        textsize+=1;
        text.textSize=textsize;
    }
    fun OnClickLow(view:View)
    {
        textsize-=1;
        text.textSize=textsize;
    }

}