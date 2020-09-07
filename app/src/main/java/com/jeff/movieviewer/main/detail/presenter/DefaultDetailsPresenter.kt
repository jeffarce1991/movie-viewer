package com.jeff.movieviewer.main.detail.presenter

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.jeff.movieviewer.database.local.Movie
import com.jeff.movieviewer.main.detail.view.DetailsView
import com.jeff.movieviewer.supplychain.photo.MovieLoader
import com.jeff.movieviewer.utilities.rx.RxSchedulerUtils
import com.jeff.movieviewer.webservices.internet.RxInternet
import com.jeff.movieviewer.webservices.usecase.loader.MovieRemoteLoader
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DefaultDetailsPresenter @Inject
constructor(
    private val rxInternet: RxInternet,
    private val rxScheduler: RxSchedulerUtils,
    private val loader: MovieLoader
) : MvpBasePresenter<DetailsView>(),
    DetailsPresenter {

    lateinit var view: DetailsView

    lateinit var disposable: Disposable
    override fun loadMovie() {
        loader.loadMovie()
            .delay(1,TimeUnit.SECONDS)
            .compose(rxScheduler.forSingle())
            .subscribe(object : SingleObserver<Movie>{
                override fun onSuccess(t: Movie) {
                    view.setMovieDetails(t)

                    view.hideShimmerPlaceholders()
                    view.stopShimmerAnimations()
                    dispose()
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d

                    view.startShimmerAnimations()
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                    view.showMessage(e.message!!)

                    view.hideShimmerPlaceholders()
                    view.stopShimmerAnimations()
                    dispose()
                }
            })
    }

    override fun attachView(view: DetailsView) {
        super.attachView(view)
        this.view = view
    }

    private fun dispose() {
        if (!disposable.isDisposed) disposable.dispose()
    }

    override fun detachView(retainInstance: Boolean) {
        dispose()
    }
}