package com.test.lbcpt.network

import com.squareup.moshi.JsonClass
import com.test.lbcpt.db.AlbumEntity
import com.test.lbcpt.model.Album

/**
  *responsible for parsing responses from the server
 */
@JsonClass(generateAdapter = true)
data class NetworkAlbumContainer(val album: List<NetworkAlbum>)

/**
 * Albums represent a data.
 */
@JsonClass(generateAdapter = true)
data class NetworkAlbum(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String)

/**
 * Convert Network results to model objects
 */
fun NetworkAlbumContainer.asDomainModel(): List<Album> {
    return album.map {
        Album(
            id = it.id,
            title = it.title,
            url = it.url,
            thumbnailUrl = it.thumbnailUrl)
    }
}

/**
 * Convert Network results to model objects
 */

fun List<NetworkAlbum>.asDatabaseModel(): List<AlbumEntity> {
    return map {
        AlbumEntity(
            id = it.id,
            title = it.title,
            url = it.url,
            thumbnailUrl = it.thumbnailUrl)
    }
}


/**
 * Convert Network results to database objects
 */
fun NetworkAlbumContainer.asDatabaseModel(): List<AlbumEntity> {
    return album.map {
        AlbumEntity(
            id = it.id,
            title = it.title,
            url = it.url,
            thumbnailUrl = it.thumbnailUrl)
    }
}
