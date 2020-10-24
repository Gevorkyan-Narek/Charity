package com.cyclone.simbirsoftprobation.presentation.presenter

import com.cyclone.simbirsoftprobation.presentation.ui.search.view.SearchResultView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class SearchResultPresenter : MvpPresenter<SearchResultView>() {

    fun setAdapter(key: Boolean) {
        viewState.setAdapter(key)
    }

    fun update(isNotBlank: Boolean) {
        viewState.update(isNotBlank)
    }
}