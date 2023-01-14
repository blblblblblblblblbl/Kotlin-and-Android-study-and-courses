package com.blblblbl.myapplication.data.repository

import com.blblblbl.myapplication.data.persistent_storage.PersistentStorage
import javax.inject.Inject

class Repository @Inject constructor(
    private val repositoryApi: RepositoryApi,
    private val persistentStorage: PersistentStorage
) {
}