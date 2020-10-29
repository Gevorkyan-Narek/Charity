package com.cyclone.simbirsoftprobation.presentation.presenter

import com.cyclone.simbirsoftprobation.presentation.ui.search.view.SearchView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class SearchPresenter: MvpPresenter<SearchView>() {

    init {
        viewState.setPager()
        viewState.setSearchOptions()
        viewState.setSearchManager()
    }

    fun updateResults(isNotBlank: Boolean) {
        viewState.updateResults(isNotBlank)
    }
}