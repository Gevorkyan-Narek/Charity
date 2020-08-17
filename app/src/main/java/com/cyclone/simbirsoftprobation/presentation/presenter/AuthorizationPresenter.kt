package com.cyclone.simbirsoftprobation.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.auth.AuthorizationView

@InjectViewState
class AuthorizationPresenter: MvpPresenter<AuthorizationView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setEnterOptions()
    }
}