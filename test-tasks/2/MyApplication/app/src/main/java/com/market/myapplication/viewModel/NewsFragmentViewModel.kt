package com.market.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.market.myapplication.data.data_classes.api.Articles
import com.market.myapplication.data.repository.NewsPagingSource
import com.market.myapplication.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsFragmentViewModel @Inject constructor(
    private val newsPagingSource: NewsPagingSource
):ViewModel() {
    val pagedNews: Flow<PagingData<Articles>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { newsPagingSource }
    ).flow.cachedIn(viewModelScope)

}