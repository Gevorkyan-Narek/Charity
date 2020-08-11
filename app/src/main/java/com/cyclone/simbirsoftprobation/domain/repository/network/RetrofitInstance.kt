package com.cyclone.simbirsoftprobation.domain.repository.network

import retrofit2.Retrofit

class RetrofitInstance {

    companion object {

        const val FIREBASE_URL = "https://i-want-to-help-7ec47.firebaseio.com/"

        private var instance: Retrofit.Builder? = null

        fun getInstance(): Retrofit.Builder {
            if (instance != null) return instance!!

            instance = Retrofit.Builder()
            return instance!!
        }
    }
}