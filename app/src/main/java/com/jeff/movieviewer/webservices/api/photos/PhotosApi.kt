package com.jeff.movieviewer.webservices.api.photos

import com.jeff.movieviewer.webservices.dto.PhotoDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotosApi {

    @GET("photos")
    fun loadPhotos(): Single<Response<List<PhotoDto>>>

    @GET("photos/{id}")
    fun loadPhotoById(@Path("id") id: Int): Single<Response<PhotoDto>>
}