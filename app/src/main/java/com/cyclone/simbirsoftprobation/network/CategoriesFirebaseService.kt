package com.cyclone.simbirsoftprobation.network

import com.cyclone.simbirsoftprobation.model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.model.Event
import retrofit2.http.GET
import rx.Single

interface CategoriesFirebaseService {

    @GET("categories.json")
    fun getCategories(): Single<List<CategoryOfHelp>>

    @GET("events.json")
    fun getEvents(): Single<List<Event>>
}