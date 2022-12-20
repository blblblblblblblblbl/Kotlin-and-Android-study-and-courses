package com.blblblbl.myapplication.data.repository.database.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blblblbl.myapplication.data.data_classes.photos.Photo
import com.blblblbl.myapplication.data.repository.database.entities.DBPhoto

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photos")
    fun getAll(): List<DBPhoto>

    @Query("SELECT photo FROM photos")
    fun getAllPhotos(): List<Photo>

    @Query("SELECT photo FROM photos LIMIT :limit OFFSET :offset")
    fun getRange(offset:Int,limit:Int):List<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dbPhoto: DBPhoto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(dbPhoto: List<DBPhoto>)

    @Query("SELECT * FROM photos")
    fun getPhotosPagingSource(): PagingSource<Int, DBPhoto>

    @Query("DELETE FROM photos")
    suspend fun clear()
}
//f loadAllUsersByPage(2,0) it will return first 2 rows from table.
//if loadAllUsersByPage(2,1) it will return 2nd and 3rd rows from table.
//but if loadAllUsersByPage(-1,10) then it will serve first 10 rows from tabl