package com.cyclone.simbirsoftprobation.network

import com.cyclone.simbirsoftprobation.model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.model.Event
import retrofit2.http.GET
import rx.Observable

interface CategoriesFirebaseService {

    @GET("categories.json")
    fun getCategories(): Observable<List<CategoryOfHelp>>

    @GET("events.json")
    fun getEvents(): Observable<List<Event>>
}