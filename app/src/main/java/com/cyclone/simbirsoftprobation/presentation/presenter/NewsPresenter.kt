package com.cyclone.simbirsoftprobation.presentation.presenter

import com.cyclone.simbirsoftprobation.domain.dagger.App
import com.cyclone.simbirsoftprobation.domain.repository.event.EventsDataRepository
import com.cyclone.simbirsoftprobation.presentation.ui.news.NewsView
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class NewsPresenter : MvpPresenter<NewsView>() {

    @Inject
    lateinit var eventsDataRepository: EventsDataRepository

    init {
        App.getComponent().inject(this)
        getEvents()
    }

    private fun getEvents() {
       eventsDataRepository
            .getEvents()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                viewState.setEvents(it)
            }
            .doOnError {
                it.printStackTrace()
                viewState.showEventsError()
            }
            .subscribe()
    }
}