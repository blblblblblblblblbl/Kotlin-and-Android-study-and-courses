package com.blblblbl.mvvm1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blblblbl.mvvm1.model.MyModel

class MyViewModel:ViewModel() {
    val text:MutableLiveData<String> by lazy{MutableLiveData<String>()}
    val model = MyModel()
    //val text:MutableLiveData<String> = MutableLiveData<String>("blblbl")

    fun changePhrase()
    {
        text.value = model.getRandomphrase()
    }
}