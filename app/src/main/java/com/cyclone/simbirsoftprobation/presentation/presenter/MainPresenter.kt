package com.cyclone.simbirsoftprobation.presentation.presenter

import com.cyclone.simbirsoftprobation.presentation.ui.main_view.MainView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    init {
        viewState.showCategoryOfHelp()
    }

    fun switchToCategories() = viewState.showCategoryOfHelp()

    fun switchToNews() = viewState.showNews()

    fun switchToSearch() = viewState.showSearch()

    fun switchToProfile() = viewState.showProfile()

    fun switchToFilter() = viewState.showFilter()
}