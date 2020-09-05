package com.jeff.movieviewer.utilities

import com.jeff.movieviewer.utilities.rx.RxSchedulerUtilsModule
import dagger.Module


@Module(includes = [RxSchedulerUtilsModule::class])
abstract class UtilityModule {

}