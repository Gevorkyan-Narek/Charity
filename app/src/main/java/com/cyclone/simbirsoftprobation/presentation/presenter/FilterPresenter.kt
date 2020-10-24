package com.cyclone.simbirsoftprobation.presentation.presenter

import com.cyclone.simbirsoftprobation.presentation.ui.filter.FilterView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class FilterPresenter: MvpPresenter<FilterView>() {

    init {
        viewState.getFilters()
    }

}