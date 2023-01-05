package com.market.myapplication.viewModel

import androidx.lifecycle.ViewModel
import com.market.myapplication.data.persistent_storage.PersistentStorage
import com.market.myapplication.domain.LocalUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val localUrlUseCase: LocalUrlUseCase
):ViewModel() {
    fun getUrl():String{
        return  localUrlUseCase.getUrl()?:""
    }
    fun saveUrl(url:String){
        localUrlUseCase.saveUrl(url)
    }
}