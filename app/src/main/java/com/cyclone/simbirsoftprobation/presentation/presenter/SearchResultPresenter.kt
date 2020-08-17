package com.cyclone.simbirsoftprobation.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.search.view.SearchResultView

@InjectViewState
class SearchResultPresenter : MvpPresenter<SearchResultView>() {

    fun setAdapter(key: Boolean) {
        viewState.setAdapter(key)
    }

    fun update(isNotBlank: Boolean) {
        viewState.update(isNotBlank)
    }
}