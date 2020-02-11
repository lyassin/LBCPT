package com.test.lbcpt.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlbumDao {
    @Query("select * from AlbumEntity")
    fun getAlbums(): LiveData<List<AlbumEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( albums: List<AlbumEntity>)
}



@Database(entities = [AlbumEntity::class], version = 1)
abstract class AlbumsDatabase: RoomDatabase() {
    abstract val albumDao: AlbumDao
}
