package com.cyclone.simbirsoftprobation.domain.repository.event

import android.util.Log
import com.cyclone.simbirsoftprobation.db.EventDataBase
import com.cyclone.simbirsoftprobation.domain.model.Event
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class EventsDataRepository : EventsRepository {

    companion object {
        private val instance = EventsDataRepository()

        fun getInstance(): EventsDataRepository = instance
    }

    override fun getEvent(id: String): Observable<Event> {
        return EventDataBase
            .getDataBase()
            .eventDAO()
            .getEvent(id)
            .subscribeOn(Schedulers.io())
            .toObservable()
    }

    override fun getEvents(): Observable<List<Event>> {
        return EventDataBase
            .getDataBase()
            .eventDAO()
            .getEvents()
            .subscribeOn(Schedulers.io())
            .toObservable()
    }

    override fun insertEvent(event: Event) {
        EventDataBase.getDataBase().eventDAO().insertEvent(event)
    }

    override fun insertEvents(events: List<Event>) {
        EventDataBase.getDataBase().eventDAO().insertEvents(events)
    }

    override fun updateEvent(event: Event) {
        EventDataBase.getDataBase().eventDAO().updateEvent(event)
    }

    override fun deleteEvent(event: Event) {
        EventDataBase.getDataBase().eventDAO().deleteEvent(event)
    }

    override fun deleteEventById(id: String) {
        EventDataBase.getDataBase().eventDAO().deleteEventById(id)
    }

    override fun deleteAll() {
        Observable.just(
            EventDataBase
                .getDataBase()
                .eventDAO()
        )
            .doOnNext { t ->
                t.deleteAll()
                Log.d("Events", "Deleted")
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}