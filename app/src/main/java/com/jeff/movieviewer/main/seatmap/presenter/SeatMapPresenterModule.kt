package com.jeff.movieviewer.main.seatmap.presenter

import dagger.Binds
import dagger.Module

@Module
abstract class SeatMapPresenterModule {

    @Binds
    abstract fun bindSeatMapPresenter(
        defaultSeatMapPresenter: DefaultSeatMapPresenter
    ): SeatMapPresenter
}