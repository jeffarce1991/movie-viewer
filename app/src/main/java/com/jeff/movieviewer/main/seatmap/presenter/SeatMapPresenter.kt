package com.jeff.movieviewer.main.seatmap.presenter

import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.jeff.movieviewer.main.list.view.MainView
import com.jeff.movieviewer.main.seatmap.view.SeatMapView

interface SeatMapPresenter: MvpPresenter<SeatMapView> {
    fun loadSeatMap()
    fun loadSchedule()
}