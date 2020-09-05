package com.jeff.movieviewer.database.usecase.local.loader

import com.jeff.movieviewer.database.local.Photo
import io.reactivex.Single

interface PhotoLocalLoader {
    fun loadAll(): Single<List<Photo>>
}