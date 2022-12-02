package com.blblblbl.myapplication.domain

import com.blblblbl.myapplication.data.PhotosRepository
import com.blblblbl.myapplication.data.data_classes.PhotoDto
import com.blblblbl.myapplication.data.data_classes.Photos
import com.blblblbl.myapplication.entity.Photo
import javax.inject.Inject

class GetPhotoUseCase @Inject constructor(
    val photosRepository: PhotosRepository
)  {
    suspend fun execute(): Photos {
        return photosRepository.getPhoto()
    }
}