package com.blblblbl.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary")
data class Word(

    /*@ColumnInfo(name = "id")
    val id:Int,*/
    @PrimaryKey
    @ColumnInfo(name = "word")
    val word:String,
    @ColumnInfo(name = "repeatCount")
    var repeatCount:Int
){
    override fun toString(): String {
        return "word:$word repeats:$repeatCount"
    }
}
