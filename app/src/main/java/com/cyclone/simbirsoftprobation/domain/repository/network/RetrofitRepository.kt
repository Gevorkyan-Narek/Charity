package com.cyclone.simbirsoftprobation.domain.repository.network

import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitRepository {

    fun fillCategoriesDB()

    fun fillEventsDB()

    fun connectFirebase(): FirebaseService {
        return RetrofitInstance
            .getInstance()
            .baseUrl(RetrofitInstance.FIREBASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    com.google.gson.GsonBuilder().setLenient().create()
                )
            )
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(
                    Schedulers.io()
                )
            )
            .build()
            .create(FirebaseService::class.java)
    }
}