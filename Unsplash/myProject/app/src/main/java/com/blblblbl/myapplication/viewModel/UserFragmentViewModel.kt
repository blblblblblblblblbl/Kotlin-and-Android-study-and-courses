package com.blblblbl.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.blblblbl.myapplication.data.LikedPhotosPagingSource
import com.blblblbl.myapplication.data.PersistantStorage
import com.blblblbl.myapplication.data.PhotosPagingSource
import com.blblblbl.myapplication.data.data_classes.photos.Photo
import com.blblblbl.myapplication.data.data_classes.public_user_info.PublicUserInfo
import com.blblblbl.myapplication.data.repository.Repository
import com.blblblbl.myapplication.domain.GetUserInfoUseCase
import com.example.example.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFragmentViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val likedPhotosPagingSource: LikedPhotosPagingSource,
    private val repository: Repository,
    private val persistantStorage: PersistantStorage
):ViewModel() {
    lateinit var pagedPhotos: Flow<PagingData<Photo>>
    private val _privateUserInfo = MutableStateFlow<UserInfo?>(null)
    val privateUserInfo = _privateUserInfo.asStateFlow()
    private val _publicUserInfo = MutableStateFlow<PublicUserInfo?>(null)
    val publicUserInfo = _publicUserInfo.asStateFlow()
    fun logout(){
        viewModelScope.launch{
            persistantStorage.clear()
            repository.clearDB()
        }
    }
    fun getUserInfo(){
        viewModelScope.launch {
            /*Log.d("MyLog","try to get userinfo")
            val user = getUserInfoUseCase.execute()
            Log.d("MyLog","userinfo" + user.toString())
            val a = user.username?.let { repository.getLikedPhotos(1, it) }
            Log.d("MyLog",a.toString())*/

            val privateUser = getUserInfoUseCase.getPrivateUserInfo()
            Log.d("MyLog","User info" + privateUser)


            privateUser.username?.let {
                val publicUser = getUserInfoUseCase.getPublicUserInfo(it)
                Log.d("MyLog","User info" + publicUser)
                likedPhotosPagingSource.userNameinit(it)
                pagedPhotos = Pager(
                    config = PagingConfig(pageSize = 10),
                    pagingSourceFactory = { likedPhotosPagingSource }
                ).flow.cachedIn(viewModelScope)
                _publicUserInfo.value = publicUser
                _privateUserInfo.value = privateUser

            }

        }
    }
}