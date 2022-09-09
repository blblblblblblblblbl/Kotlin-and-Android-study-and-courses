package com.blblblbl.pomodoromvvm.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blblblbl.pomodoromvvm.model.MyModel

class MyViewModel(/*val context: Context*/):ViewModel() {
    val time:MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    //val model = MyModel(context)
    fun startTimer()
    {
        /*model.serviceInit()
        model.startTimer()*/
    }
}