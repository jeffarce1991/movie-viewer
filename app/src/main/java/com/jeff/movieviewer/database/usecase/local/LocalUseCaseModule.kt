package com.jeff.movieviewer.database.usecase.local

import com.jeff.movieviewer.database.usecase.local.loader.DefaultPhotoLocalLoader
import com.jeff.movieviewer.database.usecase.local.loader.PhotoLocalLoader
import com.jeff.movieviewer.database.usecase.local.saver.DefaultPhotoLocalSaver
import com.jeff.movieviewer.database.usecase.local.saver.PhotoLocalSaver
import dagger.Binds
import dagger.Module

@Module
interface LocalUseCaseModule {
    @Binds
    fun bindPhotoLocalLoader(implementation: DefaultPhotoLocalLoader): PhotoLocalLoader

    @Binds
    fun bindPhotoLocalSaver(implementation: DefaultPhotoLocalSaver): PhotoLocalSaver
}