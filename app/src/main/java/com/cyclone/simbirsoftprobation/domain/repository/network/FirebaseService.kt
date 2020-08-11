package com.cyclone.simbirsoftprobation.domain.repository.network

import com.cyclone.simbirsoftprobation.domain.model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.domain.model.Event
import retrofit2.http.GET
import rx.Observable

interface FirebaseService {

    @GET("categories.json")
    fun getCategories(): Observable<List<CategoryOfHelp>>

    @GET("events.json")
    fun getEvents(): Observable<List<Event>>
}