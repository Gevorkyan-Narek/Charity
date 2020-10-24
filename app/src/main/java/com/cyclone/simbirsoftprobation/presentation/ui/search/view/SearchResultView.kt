package com.cyclone.simbirsoftprobation.presentation.ui.search.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchResultView: MvpView {

    fun setAdapter(key: Boolean)
    fun update(isNotBlank: Boolean)
}