package com.market.catfacts.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.market.catfacts.model.CatFact
import com.market.catfacts.service.repository.CatFactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatFactViewModel @Inject constructor(
    private val catFactRepository: CatFactRepository
) :ViewModel() {
    private val _catFact = MutableLiveData<CatFact>()
    val catFact = _catFact

    fun loadCatFact (){
        viewModelScope.launch {
            try {
                _catFact.value = catFactRepository.getCatFact()
            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }

    }
}