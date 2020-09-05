package com.jeff.movieviewer.webservices.observer.single

import androidx.annotation.CallSuper
import com.jeff.movieviewer.webservices.exception.SessionExpiredException
import com.jeff.movieviewer.webservices.observer.BaseSessionAwareObserver

import io.reactivex.SingleObserver

abstract class SessionAwareSingleObserver<T> : BaseSessionAwareObserver, SingleObserver<T> {

    @CallSuper
    override fun onError(e: Throwable) {
        if (e is SessionExpiredException) {
            onSessionExpiredError()
        } else {
            onCommonError(e)
        }
    }

    override fun onSessionExpiredError() {}

    override fun onCommonError(e: Throwable) {

    }
}
