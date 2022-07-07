package com.example.myapp2

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ContentInfoCompat
import com.example.myapp2.databinding.Activity2Binding
const val  MaxSize : Float = 20f;
const val  MinSize : Float = 5f;
class Activity3: AppCompatActivity()
{
    var textsize:Float = 10f;
    lateinit var bindClass: Activity2Binding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindClass = Activity2Binding.inflate(layoutInflater);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide();*/
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(bindClass.root)
        bindClass.textView.textSize=textsize;
    }
    fun OnClick(view: View)
    {
        bindClass.textView.text = "Hello!"
    }
    fun OnClickBye(view: View)
    {
        bindClass.textView.text = "See you)"
    }
    fun OnClickBig(view: View)
    {
        if (textsize==MinSize)
        {
            bindClass.button4.visibility = View.VISIBLE;
        }
        if (textsize==MaxSize)
        {
            bindClass.button3.visibility = View.INVISIBLE;
            return;
        }
        textsize += 1;
        bindClass.textView.textSize = textsize;
    }
    fun OnClickLow(view: View)
    {
        if (textsize==MaxSize)
        {
            bindClass.button3.visibility = View.VISIBLE;

        }
        if (textsize==MinSize)
        {
            bindClass.button4.visibility = View.INVISIBLE;
            return;
        }
        textsize -= 1;
        bindClass.textView.textSize = textsize;
    }
}