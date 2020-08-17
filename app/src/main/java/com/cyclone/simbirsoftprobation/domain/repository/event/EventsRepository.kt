package com.cyclone.simbirsoftprobation.domain.repository.event

import com.cyclone.simbirsoftprobation.domain.model.Event
import io.reactivex.Observable

interface EventsRepository {

    fun getEvent(id: String): Observable<Event>

    fun getEvents(): Observable<List<Event>>

    fun insertEvent(event: Event)

    fun insertEvents(events: List<Event>)

    fun updateEvent(event: Event)

    fun deleteEvent(event: Event)

    fun deleteEventById(id: String)

    fun deleteAll()
}