package com.jeff.movieviewer.main.detail.view

import com.hannesdorfmann.mosby.mvp.MvpView
import com.jeff.movieviewer.database.local.Movie

interface DetailsView : MvpView {

    fun stopShimmerAnimations()
    fun startShimmerAnimations()
    fun hideShimmerPlaceholders()
    fun showMessage(message: String)

    fun setMovieDetails(movie: Movie)

}
