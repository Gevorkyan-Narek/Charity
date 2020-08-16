package com.cyclone.simbirsoftprobation.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.main_view.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    companion object {
        const val CATEGORIES = 0
        const val EVENTS = 1
        const val SEARCH = 2
        const val PROFILE = 3
    }

    fun showFragment(fragment: Int) {
        when(fragment) {
            CATEGORIES -> viewState.showCategoryOfHelp()
            EVENTS -> viewState.showNews()
            SEARCH -> viewState.showSearch()
            PROFILE -> viewState.showProfile()
        }
    }
}