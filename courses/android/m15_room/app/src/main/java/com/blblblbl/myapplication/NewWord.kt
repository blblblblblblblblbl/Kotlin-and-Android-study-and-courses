package com.blblblbl.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class NewWord(

    /*@ColumnInfo(name = "id")
    val id:Int?=null,*/
    @PrimaryKey
    @ColumnInfo(name = "word")
    val word:String,
    @ColumnInfo(name = "repeatCount")
    var repeatCount:Int
)