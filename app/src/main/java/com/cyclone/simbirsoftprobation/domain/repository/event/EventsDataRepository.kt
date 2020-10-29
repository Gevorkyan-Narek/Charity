package com.cyclone.simbirsoftprobation.domain.repository.event

import com.cyclone.simbirsoftprobation.data.dao.EventDAO
import com.cyclone.simbirsoftprobation.domain.dagger.App
import com.cyclone.simbirsoftprobation.domain.model.Event
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EventsDataRepository @Inject constructor() : EventsRepository {

    @Inject
    lateinit var eventDAO: EventDAO

    init {
        App.getComponent().inject(this)
    }

    companion object {
        private val instance = EventsDataRepository()

        fun getInstance(): EventsDataRepository = instance
    }

    override fun getEvent(id: String): Single<Event> {
        return eventDAO.getEvent(id).subscribeOn(Schedulers.io())
    }

    override fun getEvents(): Flowable<List<Event>> {
        return eventDAO.getEvents()
    }

    override fun insertEvent(event: Event) {
        eventDAO.insertEvent(event)
    }

    override fun insertEvents(events: List<Event>) {
        eventDAO.insertEvents(events)
    }

    override fun updateEvent(event: Event) {
        eventDAO.updateEvent(event)
    }

    override fun deleteEvent(event: Event) {
        eventDAO.deleteEvent(event)
    }

    override fun deleteEventById(id: String) {
        eventDAO.deleteEventById(id)
    }

    override fun deleteAll() {
        Observable.fromCallable { eventDAO }
            .doOnNext { t ->
                t.deleteAll()
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}