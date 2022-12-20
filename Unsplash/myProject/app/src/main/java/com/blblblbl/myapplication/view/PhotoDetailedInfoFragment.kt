package com.blblblbl.myapplication.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.blblblbl.myapplication.R
import com.blblblbl.myapplication.data.data_classes.photo_detailed.DetailedPhotoInfo
import com.blblblbl.myapplication.viewModel.PhotoDetailedInfoFragmentViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

@AndroidEntryPoint
class PhotoDetailedInfoFragment : Fragment() {
    private val viewModel:PhotoDetailedInfoFragmentViewModel by viewModels()
    private var photoId:String? = null

    /*private val launcherDownload : (String) -> Unit = {imageURL->
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ map->
            if (map.values.all { it }){
                viewModel.downloadImage(imageURL)
            }
            else {
                Toast.makeText(context,"storage permissions isn't granted", Toast.LENGTH_LONG).show()
            }

        }
    }*/
    val launcher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){map->
        Log.d("MyLog","$map")
        if (map.values.all { it }){
            Toast.makeText(context,"storage permissions granted", Toast.LENGTH_LONG).show()
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
        //lsdA8QpWN_A id example
        photoId?.let { viewModel.getPhotoById(it)}
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                screen(detailedPhotoInfo = viewModel.detailedPhotoInfo)
            }
        }
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
                            modifier = Modifier.clickable { isLiked=!isLiked }
                        )
                    }
                    else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_favorite_border_24),
                            contentDescription = "like icon",
                            tint = Color.White,
                            modifier = Modifier.clickable { isLiked=!isLiked }
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
            Row() {
                Icon(painter = painterResource(id = R.drawable.ic_outline_location_on_24), contentDescription = "location icon", tint = Color.Black)
                Text(text = "${detailedPhotoInfo.location?.city}", color = textColor, fontSize = textSizeCommon)
            }
            var hashTags:String = ""
            detailedPhotoInfo.tags.forEach {
                hashTags += "#${it.title}"
            }
            Text(text = hashTags, color = textColor, fontSize = textSizeCommon, modifier = Modifier.padding(20.dp))
            Row() {
                val exif = detailedPhotoInfo.exif
                Column() {
                    Text(text = "${stringResource(id = R.string.made_with_camera)}: ${exif?.make}",color = textColor, fontSize = textSizeCommon)
                    Text(text = "${stringResource(id = R.string.camera_Model)}: ${exif?.model}",color = textColor, fontSize = textSizeCommon)
                    Text(text = "${stringResource(id = R.string.exposure)}: ${exif?.exposureTime}",color = textColor, fontSize = textSizeCommon)
                    Text(text = "${stringResource(id = R.string.aperture)}: ${exif?.aperture}",color = textColor, fontSize = textSizeCommon)
                    Text(text = "${stringResource(id = R.string.focal_length)}: ${exif?.focalLength}",color = textColor, fontSize = textSizeCommon)
                    Text(text = "${stringResource(id = R.string.iso)}: ${exif?.iso}",color = textColor, fontSize = textSizeCommon)
                }
                Spacer(modifier = Modifier.weight(1f))
                Column() {
                    Text(text = "${stringResource(id = R.string.about)} @${detailedPhotoInfo.user?.username}:",color = textColor, fontSize = textSizeCommon)
                    Text(text = "${detailedPhotoInfo.user?.bio}:",color = textColor, fontSize = textSizeCommon)
                }
            }
            Row(modifier = Modifier.align(End)) {
                Text(text = "${stringResource(id = R.string.download)} (${detailedPhotoInfo.downloads})",color = textColor, fontSize = textSizeCommon)
                Icon(painter = painterResource(id = R.drawable.ic_baseline_download_24), contentDescription = "download icon", tint = Color.Black,
                    modifier = Modifier.clickable {
                        downloadPhoto(detailedPhotoInfo.urls?.raw.toString())
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
    private fun downloadPhoto(imageURL:String){
        val isAllGranted = REQUEST_PERMISSIONS.all { permission->
            context?.let { ContextCompat.checkSelfPermission(it,permission) } == PackageManager.PERMISSION_GRANTED }
        if (isAllGranted){
            Toast.makeText(context,"storage permission is Granted",Toast.LENGTH_LONG).show()
            //viewModel.downloadImage(imageURL)
        }
        else{
            /*val launcher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){map->
                if (map.values.all { it }){
                    viewModel.downloadImage(imageURL)
                }
                else {
                    Toast.makeText(context,"storage permissions isn't granted", Toast.LENGTH_LONG).show()
                }
            }*/

            launcher.launch(REQUEST_PERMISSIONS)
        }

        //viewModel.downloadImage(imageURL)
        downloadImage(imageURL)
    }
    /*private val launcherDownload : (String) -> Unit = {imageURL->
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ map->
            if (map.values.all { it }){
                viewModel.downloadImage(imageURL)
            }
            else {
                Toast.makeText(context,"storage permissions isn't granted", Toast.LENGTH_LONG).show()
            }
        }
    }*/
    fun Context.getAppName(): String = applicationInfo.loadLabel(packageManager).toString()
    fun downloadImage(imageURL: String) {
        Log.d("MyLog","view model downloading image")
        val dirPath = Environment.getExternalStorageDirectory().absolutePath + "/" + requireContext().getAppName().replace(" ","") + "/"
        Log.d("MyLog","dirPath:$dirPath")
        val dir = File(dirPath)
        val fileName = imageURL.substring(imageURL.lastIndexOf('/') + 1)
        Glide.with(requireContext())
            .load(imageURL)
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    val bitmap = (resource as BitmapDrawable).bitmap
                    Toast.makeText(context, "Saving Image...", Toast.LENGTH_SHORT).show()
                    saveImage(bitmap, dir, fileName)
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    Toast.makeText(
                        context,
                        "Failed to Download Image! Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
    private fun saveImage(image: Bitmap, storageDir: File, imageFileName: String) {
        var successDirCreated = true
        Log.d("MyLog","storageDir.exists():${storageDir.exists()} ")
        if (!storageDir.exists()) {
            Log.d("MyLog","!storageDir.exists()")
            successDirCreated = storageDir.mkdir()
        }
        if (successDirCreated) {
            val imageFile = File(storageDir, "aaaaaaaaaa")
            Log.d("MyLog","storageDir.absolutePath: "+storageDir.absolutePath)
            Log.d("MyLog","imageFileName: "+imageFileName)
            val savedImagePath = imageFile.absolutePath
            val fOut: OutputStream = FileOutputStream(imageFile)
            image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
            fOut.close()
            Toast.makeText(context, "Image Saved!", Toast.LENGTH_SHORT).show()
            /*try {
                val fOut: OutputStream = FileOutputStream(imageFile)
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
                fOut.close()
                Toast.makeText(context, "Image Saved!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(context, "Error while saving image!", Toast.LENGTH_SHORT)
                    .show()
                e.printStackTrace()
            }*/
        } else {
            Toast.makeText(context, "Failed to make folder!", Toast.LENGTH_SHORT).show()
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