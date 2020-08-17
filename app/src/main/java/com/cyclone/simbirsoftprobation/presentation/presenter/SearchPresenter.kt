package com.cyclone.simbirsoftprobation.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.search.view.SearchView

@InjectViewState
class SearchPresenter: MvpPresenter<SearchView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setPager()
        viewState.setSearchOptions()
        viewState.setSearchManager()
    }

    fun updateResults(isNotBlank: Boolean) {
        viewState.updateResults(isNotBlank)
    }
}