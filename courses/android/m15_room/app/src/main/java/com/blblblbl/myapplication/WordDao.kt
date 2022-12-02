package com.blblblbl.myapplication

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM dictionary")
    fun getAll(): Flow<List<Word>>

    @Query("SELECT * FROM dictionary WHERE word LIKE :name ")
    suspend fun getWord(name:String): Word

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(word: Word)

    @Delete
    suspend fun delete(word: Word)

    @Query("DELETE FROM dictionary")
    suspend fun clear()

    @Update
    suspend fun update(word: Word)

    @Query("UPDATE dictionary SET repeatCount = repeatCount + 1 where word LIKE :name" )
    suspend fun countIncUpdate(name:String)
}