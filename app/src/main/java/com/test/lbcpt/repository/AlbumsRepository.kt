package com.test.lbcpt.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.test.lbcpt.db.AlbumsDatabase
import com.test.lbcpt.db.asDomainModel
import com.test.lbcpt.model.Album
import com.test.lbcpt.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.test.lbcpt.di.dagger
import timber.log.Timber
import javax.inject.Inject

/**
 * Repository for fetching data from the network and storing them on disk
 */
class AlbumsRepository @Inject constructor() {

    init {
        Timber.i("new AlbumsRepository instance")
        dagger.inject(this)
    }
    @Inject
    lateinit var database: AlbumsDatabase
    val albums: LiveData<List<Album>> = Transformations.map(database.albumDao.getAlbums()) {
        it.asDomainModel()
    }
    @Inject
    lateinit var albumService: AlbumService

    /**
     * Refresh the Data stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     */
    suspend fun refreshAlbums() {
        withContext(Dispatchers.IO) {
            Timber.i("refreshAlbums from remote")
            val playlist = albumService.getAlbums().await()
            database.albumDao.insertAll(playlist.asDatabaseModel())

        }
    }

}