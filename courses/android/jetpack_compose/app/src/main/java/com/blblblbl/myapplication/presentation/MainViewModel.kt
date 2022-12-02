package com.blblblbl.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.blblblbl.myapplication.data.CharactersPagingSource
import com.example.example.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val charactersPagingSource: CharactersPagingSource
):ViewModel() {
    val pagedCharacters:Flow<PagingData<Results>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { charactersPagingSource }
    ).flow.cachedIn(viewModelScope)
}