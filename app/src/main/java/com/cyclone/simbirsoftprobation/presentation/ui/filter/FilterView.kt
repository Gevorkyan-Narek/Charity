package com.cyclone.simbirsoftprobation.presentation.ui.filter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface FilterView: MvpView {

    fun getFilters()
}