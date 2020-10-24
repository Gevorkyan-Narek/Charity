package com.cyclone.simbirsoftprobation.presentation.ui.category_of_help

import com.cyclone.simbirsoftprobation.domain.model.CategoryOfHelp
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CategoryOfHelpView: MvpView {

    fun showCategories(categories: List<CategoryOfHelp>)
}