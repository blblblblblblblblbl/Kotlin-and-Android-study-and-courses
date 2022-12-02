package com.blblblbl.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 2)
abstract class AppDataBase:RoomDatabase() {
    abstract fun wordDao():WordDao
}