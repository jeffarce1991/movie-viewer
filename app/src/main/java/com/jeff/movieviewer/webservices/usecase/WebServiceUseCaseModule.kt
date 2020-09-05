package com.jeff.movieviewer.webservices.usecase

import com.jeff.movieviewer.webservices.usecase.loader.DefaultPhotoRemoteLoader
import com.jeff.movieviewer.webservices.usecase.loader.PhotoRemoteLoader
import dagger.Binds
import dagger.Module

@Module
interface WebServiceUseCaseModule {

    @Binds
    fun bindPhotoRemoteLoader(
            defaultPhotoRemoteLoader: DefaultPhotoRemoteLoader):
            PhotoRemoteLoader
}