package com.jeff.movieviewer.main.list.view

import com.hannesdorfmann.mosby.mvp.MvpView
import com.jeff.movieviewer.database.local.Photo

interface MainView : MvpView {
     fun hideProgress()
     fun showProgress()
     fun showProgressRemote()
     fun showProgressLocal()

     fun showLoadingDataFailed()
     fun showToast(message: String)
     fun generateDataList(photos: List<Photo>)
}