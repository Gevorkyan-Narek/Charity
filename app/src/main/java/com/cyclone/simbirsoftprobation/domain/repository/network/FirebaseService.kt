package com.cyclone.simbirsoftprobation.domain.repository.network

import com.cyclone.simbirsoftprobation.domain.model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.domain.model.Event
import retrofit2.http.GET
import rx.Single

interface FirebaseService {

    @GET("categories.json")
    fun getCategories(): Single<List<CategoryOfHelp>>

    @GET("events.json")
    fun getEvents(): Single<List<Event>>
}