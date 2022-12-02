package com.blblblbl.myapplication

import android.app.Application
import androidx.room.Room
import com.blblblbl.myapplication.data.PhotoDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp:Application() {
}