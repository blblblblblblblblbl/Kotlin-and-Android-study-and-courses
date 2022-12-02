package com.blblblbl.myapplication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel():ViewModel() {
    //val result:MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()
    var searchStr:String = ""

    fun search(text:String){
        viewModelScope.launch{
            _state.value = State.Loading
            searchStr = text
            delay(3000)
            _state.value = State.Fail
        }
    }
}