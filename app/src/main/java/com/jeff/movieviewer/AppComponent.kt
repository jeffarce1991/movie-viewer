package com.jeff.movieviewer

import android.app.Application
import com.jeff.movieviewer.database.DatabaseModule
import com.jeff.movieviewer.webservices.internet.RxInternetModule
import com.jeff.movieviewer.main.MainModule
import com.jeff.movieviewer.supplychain.photo.PhotoUseCaseModule
import com.jeff.movieviewer.utilities.UtilityModule
import com.jeff.movieviewer.webservices.api.ApiModule
import com.jeff.movieviewer.webservices.usecase.WebServiceUseCaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [AndroidSupportInjectionModule::class,
    AndroidSupportInjectionModule::class,
    MainModule::class,
    AppModule::class,
    RxInternetModule::class,
    UtilityModule::class,
    DatabaseModule::class,
    ApiModule::class,
    WebServiceUseCaseModule::class,
    PhotoUseCaseModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(myApplication: MyApplication)
}