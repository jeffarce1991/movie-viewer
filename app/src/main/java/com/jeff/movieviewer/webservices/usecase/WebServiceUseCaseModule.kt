package com.jeff.movieviewer.webservices.usecase

import com.jeff.movieviewer.webservices.usecase.loader.DefaultMovieRemoteLoader
import com.jeff.movieviewer.webservices.usecase.loader.DefaultPhotoRemoteLoader
import com.jeff.movieviewer.webservices.usecase.loader.MovieRemoteLoader
import com.jeff.movieviewer.webservices.usecase.loader.PhotoRemoteLoader
import dagger.Binds
import dagger.Module

@Module
interface WebServiceUseCaseModule {

    @Binds
    fun bindPhotoRemoteLoader(
            defaultPhotoRemoteLoader: DefaultPhotoRemoteLoader):
            PhotoRemoteLoader
    @Binds
    fun bindMovieRemoteLoader(
            defaultMovieRemoteLoader: DefaultMovieRemoteLoader
    ): MovieRemoteLoader
}