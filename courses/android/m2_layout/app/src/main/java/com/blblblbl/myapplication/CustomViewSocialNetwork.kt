package com.blblblbl.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.blblblbl.myapplication.databinding.SocialNetworkPostBinding

class CustomViewSocialNetwork
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr:Int = 0
):LinearLayout(context,attrs,defStyleAttr)
{
    private val binding = SocialNetworkPostBinding.inflate(LayoutInflater.from(context))
    init{
        addView(binding.root)
    }
    fun firstStrSetText(str:String){
        binding.str1.text = str
    }
    fun secondStrSetText(str:String){
        binding.str2.text = str
    }
}