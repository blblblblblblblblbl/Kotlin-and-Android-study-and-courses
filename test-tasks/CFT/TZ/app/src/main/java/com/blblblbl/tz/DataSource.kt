package com.blblblbl.tz

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder



class CacheDataSource(context: Context){
    companion object{
        private const val PREFERENCES_FILE_NAME = "PREFERENCES_FILE_NAME"
    }
    var sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)


    /*fun with(application: Application) {
        sharedPreferences = application.getSharedPreferences(
            PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }*/

    fun <T> saveobj(key: String, obj: T) {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()
        val jsonString = gson.toJson(obj)
        //jsonString {"date":"3922-09-21","firstName":"aaaa","password":"aaaaaa","secondName":"aaaa"}
        //val jsonString = GsonBuilder().create().toJson(obj)
        sharedPreferences.edit().putString(key,jsonString).apply()
    //jsonString = {"date":"Sep 21, 3922","firstName":"aaaaa","password":"aaaaaa","secondName":"aaaaaa"}
    }

    inline fun <reified T> getobj(key: String): T? {
        val value = sharedPreferences.getString(key,null)
        val a = "a"
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()
        return gson.fromJson(value,T::class.java)
        //return GsonBuilder().create().fromJson(value,T::class.java)
        // value = {"date":"Sep 21, 3922","firstName":"aaaa","password":"aaaaaa","secondName":"aaaa"}
    }
}