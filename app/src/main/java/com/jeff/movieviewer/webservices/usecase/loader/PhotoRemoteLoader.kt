package com.jeff.movieviewer.webservices.usecase.loader

import com.jeff.movieviewer.webservices.dto.PhotoDto
import io.reactivex.Single

interface PhotoRemoteLoader {

    fun loadAll(): Single<List<PhotoDto>>
}