package com.blblblbl.myapplication

import android.content.Context

class RepositoryImpl(private val context: Context):Repository {
    val sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)
    private var localText:String? = null
    private fun getDataFromSharedPreference(context: Context): String?{
        return  sharedPreferences.getString(SHARED_PREFS_KEY,null)
    }
    private fun getDataFromLocalVariable(): String?{
        return localText
    }
    override fun saveText(text: String){
        localText = text
        sharedPreferences.edit().putString(SHARED_PREFS_KEY,text).apply()
    }
    override fun clearText(){
        localText = null
        sharedPreferences.edit().clear().apply()
    }
    override fun getText(): String{
        return getDataFromLocalVariable() ?: (getDataFromSharedPreference(context) ?: "")
    }
    companion object{
        const val PREFERENCE_NAME = "preference_nem"
        const val SHARED_PREFS_KEY = "shared_prefs_key"
    }
}
interface Repository{
    fun saveText(text: String)
    fun clearText()
    fun getText(): String
}