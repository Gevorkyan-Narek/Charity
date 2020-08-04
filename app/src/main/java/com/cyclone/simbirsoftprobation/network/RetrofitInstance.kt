package com.cyclone.simbirsoftprobation.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers

class RetrofitInstance {

    companion object {
        val instance: CategoriesFirebaseService = Retrofit.Builder()
            .baseUrl("https://i-want-to-help-7ec47.firebaseio.com/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(CategoriesFirebaseService::class.java)
    }
}