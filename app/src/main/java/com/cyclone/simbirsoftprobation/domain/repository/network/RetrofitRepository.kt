package com.cyclone.simbirsoftprobation.domain.repository.network

interface RetrofitRepository {

    fun fillCategoriesDB()

    fun fillEventsDB()

    fun connectFirebase(): FirebaseService {
        return RetrofitInstance
            .getInstance()
            .baseUrl(RetrofitInstance.FIREBASE_URL)
            .addConverterFactory(
                retrofit2.converter.gson.GsonConverterFactory.create(
                    com.google.gson.GsonBuilder().setLenient().create()
                )
            )
            .addCallAdapterFactory(
                retrofit2.adapter.rxjava.RxJavaCallAdapterFactory.createWithScheduler(
                    rx.schedulers.Schedulers.io()
                )
            )
            .build()
            .create(FirebaseService::class.java)
    }
}