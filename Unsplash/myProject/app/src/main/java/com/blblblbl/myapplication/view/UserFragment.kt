package com.blblblbl.myapplication.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.blblblbl.myapplication.R
import com.blblblbl.myapplication.data.data_classes.photos.Photo
import com.blblblbl.myapplication.data.data_classes.public_user_info.PublicUserInfo
import com.blblblbl.myapplication.viewModel.UserFragmentViewModel
import com.example.example.UserInfo
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@AndroidEntryPoint
class UserFragment : Fragment() {
    private val viewModel:UserFragmentViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getUserInfo()
        return ComposeView(requireContext()).apply {
            setContent {
                val openDialog = remember { mutableStateOf(false) }
                Scaffold(
                    topBar = {
                        UserTopBar(onLogOutClicked = { openDialog.value = true })
                    }
                ) {
                    val antiWarning = it
                    if (openDialog.value) {
                        AlertDialog(
                            onDismissRequest = {
                                openDialog.value = false
                            },
                            title = { Text(text = stringResource(id = R.string.action_confirmation)) },
                            text = { Text(stringResource(id = R.string.logout_confirmation)) },
                            confirmButton = {
                                Button(
                                    //modifier = Modifier.weight(1f),
                                    onClick = {
                                        viewModel.logout()
                                        openDialog.value = false },
                                    content = {
                                        Text(stringResource(id = R.string.logout))
                                    }
                                )
                            },
                            dismissButton = {
                                Button(
                                    //modifier = Modifier.weight(1f),
                                    onClick = { openDialog.value = false }
                                ) {
                                    Text(stringResource(id = R.string.cancel))
                                }
                            }
                        )
                    }
                    Surface(modifier = Modifier.padding(top = it.calculateTopPadding())) {
                        screen(privateUserInfo = viewModel.privateUserInfo, publicUserInfo = viewModel.publicUserInfo)
                    }

                }
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun UserTopBar(
        onLogOutClicked: () -> Unit
    ){

        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.user),
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            actions = {
                IconButton(onClick = onLogOutClicked) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = stringResource(id = R.string.logout_icon_description)
                    )
                }
            }
        )
    }
    @Composable
    fun screen(privateUserInfo: StateFlow<UserInfo?>,publicUserInfo: StateFlow<PublicUserInfo?>){
        val privateInfoState = privateUserInfo.collectAsState().value
        val publicInfoState = publicUserInfo.collectAsState().value
        privateInfoState?.let {
            Column() {
                UserInfo(it,publicInfoState)
                likedPhotosList(viewModel.pagedPhotos)
            }
        }
    }
    
    @Composable
    fun UserInfo(userInfo: UserInfo,publicUserInfo: PublicUserInfo?){
        val textColor = Color.Black
        val textSizeName = 30.sp
        val textSizeCommon = 20.sp
        val textSizeUserName = 15.sp
        val avatar:String? = publicUserInfo?.profileImage?.large
        Row() {
            GlideImage(imageModel = {avatar}, modifier = Modifier.clip(CircleShape))
            Column() {
                Log.d("MyLog","User:${userInfo}")
                Text(text = "${userInfo.firstName} ${userInfo.lastName}", color = textColor, fontSize = textSizeName, fontWeight = FontWeight.Bold)
                Text(text = "${userInfo.username}",color = textColor, fontSize = textSizeUserName)
                userInfo.bio?.let{bio->
                    Text(text = "${bio}", fontSize = textSizeCommon, modifier = Modifier.padding(top = 15.dp, bottom = 15.dp), fontWeight = FontWeight.Bold,color = textColor)
                }
                userInfo.location?.let { location->
                    Row() {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_outline_location_on_24),
                            contentDescription = stringResource(id = R.string.location_icon_description)
                        )
                        Text(text = "${location}", fontSize = textSizeCommon, color = textColor)
                    }
                }
                userInfo.email?.let { email->
                    Row() {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_outline_mail_24),
                            contentDescription = stringResource(id = R.string.mail_icon_description)
                        )
                        Text(text = "${email}", fontSize = textSizeCommon, color = textColor)
                    }
                }
                userInfo.downloads?.let {downloads->
                    Row() {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_download_24),
                            contentDescription = stringResource(id = R.string.download_icon_description)
                        )
                        Text(text = "${downloads}", fontSize = textSizeCommon, color = textColor)
                    }
                }

            }
        }
    }


    @Composable
    fun likedPhotosList(photos: Flow<PagingData<Photo>>){
        val lazyPhotosItems: LazyPagingItems<Photo> = photos.collectAsLazyPagingItems()
        LazyColumn{
            items(lazyPhotosItems){item->
                item?.let { PhotoScreen(photo = it)}
            }
        }
        lazyPhotosItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    LoadingView(modifier = Modifier.fillMaxSize())
                }
                loadState.append is LoadState.Loading -> {
                    LoadingItem()
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyPhotosItems.loadState.refresh as LoadState.Error

                    ErrorItem(
                        message = e.error.localizedMessage!!,
                        modifier = Modifier.fillMaxSize(),
                        onClickRetry = { retry() }
                    )

                }
                loadState.append is LoadState.Error -> {
                    val e = lazyPhotosItems.loadState.append as LoadState.Error

                    ErrorItem(
                        message = e.error.localizedMessage!!,
                        onClickRetry = { retry() }
                    )

                }
            }
        }
    }
    @Composable
    fun PhotoScreen(photo: Photo){
        val textColor = Color.White
        val textSizeTotalLikes = 15.sp
        val textSizeName = 15.sp
        val textSizeUserName = 10.sp
        var isLiked by remember { mutableStateOf(photo.likedByUser?:false) }
        Surface(modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(10.dp)
            /*.clickable {
                val bundle = bundleOf()
                bundle.putString(PhotoDetailedInfoFragment.PHOTO_ID_KEY, photo.id)
                findNavController().navigate(
                    R.id.action_collectionPhotoListFragment_to_photoDetailedInfoFragment2,
                    bundle
                )
            }*/) {
            GlideImage(imageModel = {photo.urls?.regular},modifier = Modifier.fillMaxSize())
            Column() {
                Spacer(modifier = Modifier.weight(1f))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val avatar:String? = photo.user?.profileImage?.large
                    GlideImage(imageModel = {avatar}, modifier = Modifier.clip(CircleShape))
                    Column(Modifier.padding(start = 5.dp)) {
                        Text(text = "${photo.user?.name}", color = textColor, fontSize = textSizeName)
                        Text(text = "@${photo.user?.username}", color = textColor, fontSize = textSizeUserName)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "${photo.likes}", color = textColor, fontSize = textSizeTotalLikes, textAlign = TextAlign.End)
                    if (isLiked) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                            contentDescription = stringResource(id = R.string.like_icon_description),
                            tint = Color.Red,
                            modifier = Modifier.clickable {
                                isLiked=!isLiked
                                photo.id?.let {viewModel.changeLike(it,isLiked)  }
                            }
                        )
                    }
                    else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_favorite_border_24),
                            contentDescription = stringResource(id = R.string.like_icon_description),
                            tint = Color.White,
                            modifier = Modifier.clickable {
                                isLiked=!isLiked
                                photo.id?.let {viewModel.changeLike(it,isLiked)  }
                            }
                        )
                    }
                }
            }
        }
    }
    @Composable
    fun LoadingView(
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    fun LoadingItem() {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }

    @Composable
    fun ErrorItem(
        message: String,
        modifier: Modifier = Modifier,
        onClickRetry: () -> Unit
    ) {
        Row(
            modifier = modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = message,
                maxLines = 1,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Red
            )
            OutlinedButton(onClick = onClickRetry) {
                Text(text = stringResource(id = R.string.try_again))
            }
        }
    }

}