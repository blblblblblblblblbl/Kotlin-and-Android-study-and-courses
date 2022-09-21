package com.blblblbl.tz

import java.util.*

class ViewModel(private val model:Model) {
    fun registerUser(firstName:String, secondName:String, date: Date, password:String){
        model.setUser(firstName,secondName,date,password)
    }
    fun saveUser(){
        model.saveUser()
    }
    fun getUser():User{ return model.getUser()}
    fun checkSaved():Boolean{
        if (model.isSavedUserExist()) model.getSavedUser()
        return model.isSavedUserExist()
    }
}