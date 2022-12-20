package com.blblblbl.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.blblblbl.myapplication.data.PhotosPagingSource
import com.blblblbl.myapplication.data.data_classes.photos.Photo
import com.blblblbl.myapplication.data.repository.CollectionPhotoPagingSource
import com.blblblbl.myapplication.domain.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CollectionPhotoListViewModel @Inject constructor(
    private val collectionPhotosPagingSource: CollectionPhotoPagingSource
):ViewModel() {
    lateinit var pagedPhotos: Flow<PagingData<Photo>>
    fun getCollectionPhotos(id:String){
        collectionPhotosPagingSource.idInit(id)
        pagedPhotos = Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { collectionPhotosPagingSource }
        ).flow.cachedIn(viewModelScope)
    }
}