package com.blblblbl.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blblblbl.myapplication.data.PhotosRepository
import com.blblblbl.myapplication.data.data_classes.Photos
import com.blblblbl.myapplication.domain.GetPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getPhotoUseCase: GetPhotoUseCase
):ViewModel() {
    @Inject lateinit var photosRepository: PhotosRepository
    private val _photos = MutableStateFlow<Photos?>(null)
    val photos = _photos.asStateFlow()

    fun reloadPhotos(){
        viewModelScope.launch {
            _photos.value = getPhotoUseCase.execute()
        }
    }
}