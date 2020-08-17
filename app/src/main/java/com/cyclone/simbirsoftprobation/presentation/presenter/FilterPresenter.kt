package com.cyclone.simbirsoftprobation.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.filter.FilterView

@InjectViewState
class FilterPresenter: MvpPresenter<FilterView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.getFilters()
    }
}