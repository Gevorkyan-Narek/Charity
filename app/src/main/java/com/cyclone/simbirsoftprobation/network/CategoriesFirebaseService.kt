package com.cyclone.simbirsoftprobation.network

import com.cyclone.simbirsoftprobation.model.CategoryOfHelp
import retrofit2.http.GET
import rx.Observable

interface CategoriesFirebaseService {

    @GET("categories.json")
    fun getCategories(): Observable<List<CategoryOfHelp>>
}