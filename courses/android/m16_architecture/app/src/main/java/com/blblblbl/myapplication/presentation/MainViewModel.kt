package com.blblblbl.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blblblbl.myapplication.data.UsefulActivitiesRepository
import com.blblblbl.myapplication.domain.GetUsefulActivityUseCase
import com.blblblbl.myapplication.entity.UsefulActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getUsefulActivityUseCase: GetUsefulActivityUseCase):ViewModel() {
    @Inject lateinit var usefulActivitiesRepository:UsefulActivitiesRepository


    private val _activity = MutableStateFlow<UsefulActivity?>(null)
    val activity = _activity.asStateFlow()

    fun reloadState(){
        viewModelScope.launch{
            _activity.value = getUsefulActivityUseCase.execute()
        }
    }

}