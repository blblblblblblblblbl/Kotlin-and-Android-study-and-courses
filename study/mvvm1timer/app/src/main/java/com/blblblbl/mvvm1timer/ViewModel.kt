package com.blblblbl.mvvm1timer

import android.graphics.ColorSpace

class ViewModel(private val model: Model) {
    private var textObservable:TextObservable? = null
    private var textCallBack = object : TextCallBack {
        override fun updateText(str: String){
            textObservable?.postValue(str)
        }
    }

    fun init(textObservable: TextObservable){
        this.textObservable = textObservable

    }
    fun clear()
    {
        textObservable = null
    }

    fun resumeCounting()
    {
        model.start(textCallBack)
    }

    fun stopCounting()
    {
        model.stop()
    }
}

open class TextObservable{

    lateinit var textCallBack: TextCallBack

    fun observe(callBack: TextCallBack){
        textCallBack = callBack
    }

    fun postValue(str: String){
        textCallBack.updateText(str)
    }
}

interface TextCallBack{
    fun updateText(str:String)
}