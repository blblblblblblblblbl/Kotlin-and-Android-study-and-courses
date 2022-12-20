package com.blblblbl.myapplication.domain

import com.blblblbl.myapplication.data.PersistantStorage
import com.blblblbl.myapplication.data.data_classes.photo_detailed.DetailedPhotoInfo
import com.blblblbl.myapplication.data.repository.Repository
import com.blblblbl.myapplication.data.data_classes.photos.Photo
import com.blblblbl.myapplication.data.repository.RepositoryApi
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: Repository
){
    suspend fun getPhotos(page: Int):List<Photo>{
        return repository.getPhotos(page)
    }
    suspend fun getPhotoById(id: String): DetailedPhotoInfo {
        return repository.getPhotoById(id)
    }
    suspend fun getCollectionPhotoList(id:String,page: Int):List<Photo>{
        return repository.getCollectionPhotoList(id,page)
    }
}