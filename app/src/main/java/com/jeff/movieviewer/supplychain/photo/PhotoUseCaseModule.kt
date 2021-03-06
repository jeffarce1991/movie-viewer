package com.jeff.movieviewer.supplychain.photo

import dagger.Binds
import dagger.Module

@Module
abstract class PhotoUseCaseModule {

    @Binds
    abstract fun bindPhotoLoader(defaultPhotoLoader: DefaultPhotoLoader): PhotoLoader

    @Binds
    abstract fun bindMovieLoader(defaultMovieLoader: DefaultMovieLoader): MovieLoader
}