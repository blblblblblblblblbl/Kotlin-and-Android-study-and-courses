package com.market.pomodorotimer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel:ViewModel() {
    val timeToAct:MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val timeWork:MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val timeRelax:MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
}