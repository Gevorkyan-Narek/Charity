package com.cyclone.simbirsoftprobation.presentation.presenter

import android.widget.TextView
import com.cyclone.simbirsoftprobation.presentation.ui.auth.AuthorizationView
import com.firebase.ui.auth.AuthUI
import com.jakewharton.rxbinding.widget.RxTextView
import moxy.InjectViewState
import moxy.MvpPresenter
import rx.Observable

@InjectViewState
class AuthorizationPresenter : MvpPresenter<AuthorizationView>() {

    companion object {
        private const val MIN_LINE_LENGTH = 5
    }

    fun loginListener(email: TextView, password: TextView) {
        Observable.combineLatest(
            RxTextView.textChanges(email),
            RxTextView.textChanges(password)
        ) { t1, t2 -> t1.length > MIN_LINE_LENGTH && t2.length > MIN_LINE_LENGTH }
            .doOnNext {
                viewState.buttonIsEnable(it)
            }
            .subscribe()
    }

    fun signInGoogle() = viewState.signIn()
}