package com.jeff.movieviewer.supplychain.photo

import com.jeff.movieviewer.database.local.Photo
import io.reactivex.Single

interface PhotoLoader {

    fun loadAll(): Single<List<Photo>>

    fun loadAllFromLocal(): Single<List<Photo>>
}