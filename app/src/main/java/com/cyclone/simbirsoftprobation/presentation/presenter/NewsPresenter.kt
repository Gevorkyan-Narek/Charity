package com.cyclone.simbirsoftprobation.presentation.presenter

import com.cyclone.simbirsoftprobation.domain.repository.event.EventsDataRepository
import com.cyclone.simbirsoftprobation.presentation.ui.news.NewsView
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class NewsPresenter : MvpPresenter<NewsView>() {

    init {
        getEvents()
    }

    private fun getEvents() {
        EventsDataRepository
            .getInstance()
            .getEvents()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.setEvents(it)
            },
                {
                    viewState.showEventsError()
                })
    }

}