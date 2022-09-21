package com.blblblbl.tz

import java.util.*

class Model(private val cacheDataSource: CacheDataSource) {
    companion object{
        const val FIRST_NAME:String = "firstname"
        const val SECOND_NAME:String = "secondname"
        const val DATE:String = "secondname"
        const val PASSWORD:String = "password"
        const val USER:String = "user"
    }
    private lateinit var user: User
    fun setUser(firstName:String, secondName:String, date: Date, password:String){
        user = User(firstName,secondName,date,password)
    }
    fun getUser():User{
        return user
    }
    fun isSavedUserExist():Boolean{
        var s = cacheDataSource.getobj<User>(USER)
        return (cacheDataSource.getobj<User>(USER)!=null)
    }
    fun getSavedUser(){
        if (isSavedUserExist()) {
            user = cacheDataSource.getobj<User>(USER)!!
        }
    }

    fun saveUser(){
        cacheDataSource.saveobj(USER,user)
    }
}