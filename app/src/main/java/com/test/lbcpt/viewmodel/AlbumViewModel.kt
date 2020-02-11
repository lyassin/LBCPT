package com.test.lbcpt.viewmodel

import androidx.lifecycle.*
import com.test.lbcpt.model.Album
import com.test.lbcpt.repository.AlbumsRepository
import com.test.lbcpt.di.dagger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

/**
 * DevByteViewModel designed to store and manage UI-related data in a lifecycle conscious way. This
 * allows data to survive configuration changes such as screen rotations. In addition, background
 * work such as fetching network results can continue through configuration changes and deliver
 * results after the new Fragment or Activity is available.
 *
 * @param application The application that this viewmodel is attached to, it's safe to hold a
 * reference to applications across rotation since Application is never recreated during actiivty
 * or fragment lifecycle events.
 */

class AlbumsViewModelProvider : ViewModelProvider.NewInstanceFactory() {

    /**
     * The data source this ViewModel will fetch results from.
     */
    @Inject
    lateinit var albumsRepository : AlbumsRepository


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        dagger.inject(this)
        return AlbumViewModel(albumsRepository) as T
    }
}
class AlbumViewModel(albumsRepository: AlbumsRepository) : ViewModel() {


    /**
     * A list of albums displayed on the screen.
     */
    val albums: LiveData<List<Album>>

    /**
     * This is the job for all coroutines started by this ViewModel.
     *
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     *
     * Since we pass viewModelJob, you can cancel all coroutines launched by uiScope by calling
     * viewModelJob.cancel()
     */
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        Timber.i("New AlbumViewModel instance")
        albums = albumsRepository.albums

        viewModelScope.launch {
            try {
                albumsRepository.refreshAlbums()
            } catch (networkError: IOException) {
            }
        }
    }

}