package com.blblblbl.mvvm1timer

import android.content.Context
import android.content.Context.MODE_PRIVATE

interface DataSource {
    fun saveInt(key:String, value:Int)
    fun getInt(key: String):Int
}
class CacheDataSource(context: Context):DataSource{
    companion object{
        const val COUNT_KEY :String = "counting"
    }
    private val sharedPreferences = context.getSharedPreferences(COUNT_KEY, MODE_PRIVATE)

    override fun saveInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key,value).apply()
    }

    override fun getInt(key: String): Int {
        return sharedPreferences.getInt(key,0)
    }
}