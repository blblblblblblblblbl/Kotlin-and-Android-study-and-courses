package com.blblblbl.myapplication.view

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.blblblbl.myapplication.R
import com.blblblbl.myapplication.data.data_classes.photo_detailed.DetailedPhotoInfo
import com.blblblbl.myapplication.viewModel.PhotoDetailedInfoFragmentViewModel
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PhotoDetailedInfoFragment : Fragment() {
    private val viewModel:PhotoDetailedInfoFragmentViewModel by viewModels()
    private var photoId:String? = null

    val launcher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){map->
        Log.d("MyLog","$map")
        if (map.values.all { it }){
            Toast.makeText(context,"storage permissions granted", Toast.LENGTH_LONG).show()
            viewModel.download()
        }
        else {
            Toast.makeText(context,"storage permissions isn't granted", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {photoId = it.getString(PHOTO_ID_KEY)  }
        Log.d("MyLog","photoId: $photoId")
        photoId?.let { viewModel.getPhotoById(it)}
        return ComposeView(requireContext()).apply {
            setContent {
                all(detailedPhotoInfo = viewModel.detailedPhotoInfo)
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun all(detailedPhotoInfo: StateFlow<DetailedPhotoInfo?>){
        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope: CoroutineScope = rememberCoroutineScope()
        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            status?.let {
                //Reset status value at first to prevent multitriggering
                //and to be available to trigger action again
                if (it){
                    viewModel.status.value = null
                    coroutineScope.launch {
                        val snackbarResult = snackbarHostState.showSnackbar(
                            message = "image saved",
                            actionLabel = "show in gallery"
                        )
                        when (snackbarResult) {
                            SnackbarResult.Dismissed -> TODO()
                            SnackbarResult.ActionPerformed -> viewModel.openGallery()
                        }
                    }
                }
            }
        })
        Scaffold(
            content = {
                val a = it
                screen(detailedPhotoInfo = detailedPhotoInfo)
            },
            snackbarHost = { SnackbarHost(snackbarHostState) }

        )
    }
    @Composable
    fun screen(detailedPhotoInfo: StateFlow<DetailedPhotoInfo?>){
        val state = detailedPhotoInfo.collectAsState().value
        state?.let {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                PhotoScreen(detailedPhotoInfo = it)
                PhotoDescription(detailedPhotoInfo = it)
            }
        }
    }
    @Composable
    fun PhotoScreen(detailedPhotoInfo: DetailedPhotoInfo){
        val textColor = Color.White
        val textSizeTotalLikes = 15.sp
        val textSizeName = 15.sp
        val textSizeUserName = 10.sp
        var isLiked by remember { mutableStateOf(detailedPhotoInfo.likedByUser?:false)}
        Surface(modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(10.dp)) {
            GlideImage(imageModel = {detailedPhotoInfo.urls?.regular},modifier = Modifier.fillMaxSize())
            Column() {
                Spacer(modifier = Modifier.weight(1f))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val avatar:String? = detailedPhotoInfo.user?.profileImage?.large
                    GlideImage(imageModel = {avatar}, modifier = Modifier.clip(CircleShape))
                    Column(Modifier.padding(start = 5.dp)) {
                        Text(text = "${detailedPhotoInfo.user?.name}", color = textColor, fontSize = textSizeName)
                        Text(text = "@${detailedPhotoInfo.user?.username}", color = textColor, fontSize = textSizeUserName)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "${detailedPhotoInfo.likes}", color = textColor, fontSize = textSizeTotalLikes, textAlign = TextAlign.End)
                    if (isLiked) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                            contentDescription = "like icon",
                            tint = Color.Red,
                            modifier = Modifier.clickable {
                                isLiked=!isLiked
                                detailedPhotoInfo.id?.let {viewModel.changeLike(it,isLiked)  }
                            }
                        )
                    }
                    else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_favorite_border_24),
                            contentDescription = "like icon",
                            tint = Color.White,
                            modifier = Modifier.clickable {
                                isLiked=!isLiked
                                detailedPhotoInfo.id?.let {viewModel.changeLike(it,isLiked)  }
                            }
                        )
                    }
                }
            }
        }
    }
    @Composable
    fun PhotoDescription(detailedPhotoInfo: DetailedPhotoInfo){
        val textColor = Color.Black
        val textSizeCommon = 15.sp
        val textSizeHashTag = 10.sp
        Column(modifier = Modifier.padding(10.dp)) {
            detailedPhotoInfo.location?.let { location->
                if (location.city!=null||location.country!=null||(location.position!=null&&location.position?.latitude!=null&&location.position?.longitude!=null)){
                    Row() {
                        Icon(painter = painterResource(id = R.drawable.ic_outline_location_on_24), contentDescription = "location icon", tint = Color.Black,
                            modifier = Modifier.clickable {
                                val latitude = location.position?.latitude
                                val longitude  = location.position?.longitude
                                Log.d("MyLog","geo:${location.position}")
                                if (latitude!=null &&longitude!=null){
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("geo:$latitude,$longitude")
                                    )
                                    startActivity(intent)
                                }
                            })
                        Text(text = "${location.city?:""} ${location.country?:""}", color = textColor, fontSize = textSizeCommon)
                    }
                }
            }

            var hashTags:String = ""
            detailedPhotoInfo.tags.forEach {
                hashTags += "#${it.title}"
            }
            if (hashTags!="") Text(text = hashTags, color = textColor, fontSize = textSizeCommon, modifier = Modifier.padding(20.dp))
            detailedPhotoInfo.exif?.let {exif->
                Row() {
                    //val exif = detailedPhotoInfo.exif
                    Column() {
                        exif.make?.let { make-> Text(text = "${stringResource(id = R.string.made_with_camera)}: ${make}",color = textColor, fontSize = textSizeCommon) }
                        exif.model?.let {model->Text(text = "${stringResource(id = R.string.camera_Model)}: ${model}",color = textColor, fontSize = textSizeCommon)}
                        exif.exposureTime?.let {exposureTime->Text(text = "${stringResource(id = R.string.exposure)}: ${exposureTime}",color = textColor, fontSize = textSizeCommon)}
                        exif.aperture?.let {aperture->Text(text = "${stringResource(id = R.string.aperture)}: ${aperture}",color = textColor, fontSize = textSizeCommon)}
                        exif.focalLength?.let {focalLength->Text(text = "${stringResource(id = R.string.focal_length)}: ${focalLength}",color = textColor, fontSize = textSizeCommon)}
                        exif.iso?.let {iso->Text(text = "${stringResource(id = R.string.iso)}: ${iso}",color = textColor, fontSize = textSizeCommon)}
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    detailedPhotoInfo.user?.bio?.let { bio->
                        Column() {
                            Text(text = "${stringResource(id = R.string.about)} @${detailedPhotoInfo.user?.username}:",color = textColor, fontSize = textSizeCommon)
                            Text(text = "${bio}:",color = textColor, fontSize = textSizeCommon)
                        }
                    }

                }
            }

            Row(modifier = Modifier.align(End)) {
                Text(text = "${stringResource(id = R.string.download)} (${detailedPhotoInfo.downloads})",color = textColor, fontSize = textSizeCommon)
                Icon(painter = painterResource(id = R.drawable.ic_baseline_download_24), contentDescription = "download icon", tint = Color.Black,
                    modifier = Modifier.clickable {
                        launcher.launch(REQUEST_PERMISSIONS)
                    })
                Icon(painter = painterResource(id = R.drawable.ic_baseline_share_24), contentDescription = "share icon", tint = Color.Black,
                    modifier = Modifier.clickable {
                        ShareCompat.IntentBuilder(requireContext())
                            .setType("text/plain")
                            .setChooserTitle("Share URL")
                            .setText("https://unsplash.com/photos/${detailedPhotoInfo.id}")
                            .startChooser(); })
            }

        }
    }

    companion object{
        const val PHOTO_ID_KEY = "photoIdKey"
        private val REQUEST_PERMISSIONS:Array<String> = buildList {
            add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            add(Manifest.permission.READ_EXTERNAL_STORAGE)
            if (Build.VERSION.SDK_INT<= Build.VERSION_CODES.P){
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }.toTypedArray()
    }
}