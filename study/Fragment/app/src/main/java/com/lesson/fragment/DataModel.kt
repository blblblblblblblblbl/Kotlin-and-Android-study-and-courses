package com.lesson.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel:ViewModel() {
    val messageForAct:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val messageForFrag1:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val messageForFrag2:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}