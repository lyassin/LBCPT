package com.test.lbcpt.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.lbcpt.model.Album


/**
 * AlbumEntity represents an album entity in the database.
 */
@Entity
data class AlbumEntity constructor(
    @PrimaryKey
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String)


/**
 * Map DatabaseAlbums to domain entities
 */
fun List<AlbumEntity>.asDomainModel(): List<Album> {
    return map {
        Album(
            id = it.id,
            title = it.title,
            url = it.url,
            thumbnailUrl = it.thumbnailUrl)
    }
}