package com.cyclone.simbirsoftprobation.domain.repository.network

import android.util.Log
import com.cyclone.simbirsoftprobation.data.db.EventDataBase
import com.cyclone.simbirsoftprobation.data.storage.Storage
import com.cyclone.simbirsoftprobation.domain.dagger.App
import com.cyclone.simbirsoftprobation.domain.executor.json_helper.JsonHelperStart
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RetrofitDataRepository @Inject constructor() {

    @Inject
    lateinit var firebaseService: FirebaseService

    init {
        App.getRetrofitComponent().inject(this)
        fillCategoriesDB()
        fillEventsDB()
    }

    fun fillCategoriesDB() {
        firebaseService
            .getCategories()
            .onErrorReturn {
                Storage.categoriesOfHelp
            }
            .doOnSuccess {
                EventDataBase.getDataBase().categoriesDAO().insertCategories(it)
            }
            .subscribe()
    }

    private fun fillEventsDB() {
        firebaseService
            .getEvents()
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                Log.d("ErrorReturn", "ErrorReturn")

                // Async
                JsonHelperStart.getInstance().startAsync()

//                     Executor
//                JsonHelperStart.getInstance().startExecutor()

//                     IntentService
//                JsonHelperStart.getInstance().startIntentService()
                null
            }
            .observeOn(Schedulers.io())
            .doOnSuccess { EventDataBase.getDataBase().eventDAO().insertEvents(it) }
            .subscribe()
    }

}