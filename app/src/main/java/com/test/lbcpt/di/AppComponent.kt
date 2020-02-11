/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.test.lbcpt.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.test.lbcpt.db.AlbumsDatabase
import com.test.lbcpt.network.AlbumServiceModule
import com.test.lbcpt.repository.AlbumsRepository
import com.test.lbcpt.viewmodel.AlbumsViewModelProvider
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

lateinit var dagger: AppComponent
fun initAppDagger(application: Application) {
    dagger = DaggerAppComponent.builder()
        .appModule(AppModule(application))
        .albumsDatabasModule(AlbumsDatabasModule())
        .albumServiceModule(AlbumServiceModule())
        .build()
}

@Singleton
@Component(modules = [AppModule::class, AlbumsDatabasModule::class, AlbumServiceModule::class])
interface AppComponent {
    fun inject(albumsViewModelProvider: AlbumsViewModelProvider)
    fun inject (albumsRepository : AlbumsRepository)
}

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun getContext(): Context = application
}

@Module
class AlbumsDatabasModule() {

    @Provides
    @Singleton
    fun getAlbumsDatabase(context: Context): AlbumsDatabase =
        Room.databaseBuilder(context.applicationContext,
            AlbumsDatabase::class.java,
            "Albums").build()
}


