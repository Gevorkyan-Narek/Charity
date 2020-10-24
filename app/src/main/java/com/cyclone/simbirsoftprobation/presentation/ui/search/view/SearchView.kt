package com.cyclone.simbirsoftprobation.presentation.ui.search.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchView: MvpView {

    fun setPager()
    fun setSearchManager()
    fun setSearchOptions()
    fun updateResults(isNotBlank: Boolean)
}