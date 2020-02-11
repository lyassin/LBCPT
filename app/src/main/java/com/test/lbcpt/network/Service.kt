package com.test.lbcpt.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * A retrofit service to fetch albums.
 */
interface AlbumService {
    @GET("technical-test.json")
    fun getAlbums(): Deferred<List<NetworkAlbum>>
}