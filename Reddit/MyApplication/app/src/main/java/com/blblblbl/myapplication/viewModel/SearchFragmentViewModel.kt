package com.blblblbl.myapplication.viewModel

import androidx.lifecycle.ViewModel
import com.blblblbl.myapplication.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
}