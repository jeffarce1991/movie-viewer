package com.jeff.movieviewer.main

import com.jeff.movieviewer.ActivityScope
import com.jeff.movieviewer.main.detail.presenter.DetailsPresenterModule
import com.jeff.movieviewer.main.detail.view.DetailsActivity
import com.jeff.movieviewer.main.list.presenter.MainPresenterModule
import com.jeff.movieviewer.main.list.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainPresenterModule::class])
    fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [DetailsPresenterModule::class])
    fun detailsActivity(): DetailsActivity
}