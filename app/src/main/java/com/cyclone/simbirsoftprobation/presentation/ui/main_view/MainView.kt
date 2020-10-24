package com.cyclone.simbirsoftprobation.presentation.ui.main_view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {

    fun showCategoryOfHelp()
    fun showNews()
    fun showSearch()
    fun showProfile()
    fun showFilter()
}