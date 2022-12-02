package com.blblblbl.myapplication

import android.app.Application
import androidx.room.Room

class MyApplication: Application() {
    lateinit var db:AppDataBase
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "db"
        ).build()
    }
}