package com.market.myapplication.domain

import com.market.myapplication.data.persistent_storage.PersistentStorage
import javax.inject.Inject

class LocalUrlUseCase @Inject constructor(
    private val storage: PersistentStorage
) {
    fun getUrl():String?{
        return  storage.getProperty(PersistentStorage.URL_INFO)
    }
    fun saveUrl(url:String){
        storage.addProperty(PersistentStorage.URL_INFO,url)
    }
}