package com.cyclone.simbirsoftprobation.presentation.ui.news

import com.cyclone.simbirsoftprobation.domain.model.Event
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface NewsView: MvpView {

    fun setEvents(events: List<Event>)
    fun showEventsError()
}