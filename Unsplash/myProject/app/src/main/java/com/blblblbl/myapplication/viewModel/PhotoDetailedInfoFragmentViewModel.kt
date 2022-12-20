package com.blblblbl.myapplication.viewModel

import android.R
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blblblbl.myapplication.data.data_classes.photo_detailed.DetailedPhotoInfo
import com.blblblbl.myapplication.domain.GetPhotosUseCase
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import javax.inject.Inject


@HiltViewModel
class PhotoDetailedInfoFragmentViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
    @ApplicationContext private val context: Context
):ViewModel() {
    private val _detailedPhotoInfo = MutableStateFlow<DetailedPhotoInfo?>(null)
    val detailedPhotoInfo = _detailedPhotoInfo.asStateFlow()
    fun getPhotoById(id:String){
        viewModelScope.launch {
            val response = getPhotosUseCase.getPhotoById(id)
            _detailedPhotoInfo.value = response
            Log.d("MyLog","single photo by id response:${response}")
        }
    }

    fun Context.getAppName(): String = applicationInfo.loadLabel(packageManager).toString()
    fun downloadImage(imageURL: String) {
        Log.d("MyLog","view model downloading image")
        val dirPath = Environment.getExternalStorageDirectory().absolutePath + "/" + context.getAppName().replace(" ","") + "/"
        Log.d("MyLog","dirPath:$dirPath")
        val dir = File(dirPath)
        val fileName = imageURL.substring(imageURL.lastIndexOf('/') + 1)
        Glide.with(context)
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
            val imageFile = File(storageDir, "imageFileName")
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
    /*fun verifyPermissions(): Boolean? {

        // This will return the current Status
        val permissionExternalMemory =
            ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permissionExternalMemory != PackageManager.PERMISSION_GRANTED) {
            val STORAGE_PERMISSIONS = arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            // If permission not granted then ask for permission real time.
            ActivityCompat.requestPermissions(context, STORAGE_PERMISSIONS, 1)
            return false
        }
        return true
    }*/
}