package com.jeff.movieviewer.main.seatmap.presenter

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.jeff.movieviewer.Constants
import com.jeff.movieviewer.database.local.Photo
import com.jeff.movieviewer.database.local.Schedule
import com.jeff.movieviewer.database.local.SeatMap
import com.jeff.movieviewer.database.usecase.local.loader.PhotoLocalLoader
import com.jeff.movieviewer.database.usecase.local.saver.PhotoLocalSaver
import com.jeff.movieviewer.webservices.exception.NoInternetException
import com.jeff.movieviewer.webservices.internet.RxInternet
import com.jeff.movieviewer.main.list.view.MainView
import com.jeff.movieviewer.main.seatmap.view.SeatMapView
import com.jeff.movieviewer.supplychain.photo.MovieLoader
import com.jeff.movieviewer.supplychain.photo.PhotoLoader
import com.jeff.movieviewer.webservices.dto.PhotoDto
import com.jeff.movieviewer.webservices.api.photos.PhotosApi
import com.jeff.movieviewer.webservices.api.RetrofitClientInstance
import com.jeff.movieviewer.utilities.rx.RxSchedulerUtils
import com.jeff.movieviewer.webservices.usecase.loader.MovieRemoteLoader
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class DefaultSeatMapPresenter @Inject
constructor(
    private val rxInternet: RxInternet,
    private val loader: MovieLoader,
    private val schedulerUtils: RxSchedulerUtils
) : MvpBasePresenter<SeatMapView>(),
    SeatMapPresenter {

    lateinit var view: SeatMapView

    lateinit var disposable: Disposable

    override fun loadSeatMap() {
        rxInternet.isConnected()
            .andThen(loader.loadSeatMap())
            .compose(schedulerUtils.forSingle())
            .subscribe(object : SingleObserver<SeatMap>{
                override fun onSuccess(t: SeatMap) {
                    view.generateSeatMap(t)
                    view.hideProgressBar()
                    dispose()
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                    view.showProgressBar()
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                    view.showMessage(e.message!!)
                    view.hideProgressBar()
                    dispose()
                }
            })

    }

    override fun loadSchedule() {
        rxInternet.isConnected()
            .andThen(loader.loadSchedule())
            .compose(schedulerUtils.forSingle())
            .subscribe(object : SingleObserver<Schedule>{
                override fun onSuccess(t: Schedule) {
                    Timber.d("==q $t")
                    view.setSchedule(t)
                    dispose()
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                    view.showMessage(e.message!!)
                    dispose()
                }
            })
    }

    override fun attachView(view: SeatMapView) {
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