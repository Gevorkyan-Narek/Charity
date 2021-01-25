package com.cyclone.simbirsoftprobation.presentation.presenter

import com.cyclone.simbirsoftprobation.presentation.ui.main_view.MainView
import com.google.firebase.auth.FirebaseAuth
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    init {
        viewState.invisibleBottomBar()
        if (FirebaseAuth.getInstance().currentUser != null)
            switchToCategories()
        else
            switchToAuth()
    }

    fun switchToCategories() {
        viewState.visibleBottomBar()
        viewState.showCategoryOfHelp()
    }

    fun switchToNews() = viewState.showNews()

    fun switchToSearch() = viewState.showSearch()

    fun switchToProfile() = viewState.showProfile()

    fun switchToFilter() = viewState.showFilter()

    fun switchToAuth() {
        viewState.invisibleBottomBar()
        viewState.showAuth()
    }
}

