package com.cyclone.simbirsoftprobation.presentation.ui.auth

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AuthorizationView: MvpView {

    fun buttonIsEnable(isEnable: Boolean)
}