package com.test.lbcpt.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
class AlbumServiceModule {

    @Provides
    @Singleton
    fun getAlbumService(): AlbumService = Retrofit.Builder()
        .baseUrl("https://static.leboncoin.fr/img/shared/")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(AlbumService::class.java)


}