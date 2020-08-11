package com.cyclone.simbirsoftprobation.domain.repository.network

import android.util.Log
import com.cyclone.simbirsoftprobation.db.EventDataBase
import com.cyclone.simbirsoftprobation.domain.model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.domain.model.Event
import com.cyclone.simbirsoftprobation.domain.executor.json_helper.JsonHelperStart
import com.cyclone.simbirsoftprobation.storage.Datas
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RetrofitDataRepository : RetrofitRepository {

    companion object {
        private val instance = RetrofitDataRepository()

        fun getInstance(): RetrofitDataRepository = instance
    }

    override fun fillCategoriesDB() {
        connectFirebase()
            .getCategories()
            .onErrorReturn {
                Datas.categoriesOfHelp
            }
            .subscribe { t: List<CategoryOfHelp>? ->
                EventDataBase.getDataBase().categoriesDAO().insertCategories(t!!)
            }
    }

    override fun fillEventsDB() {
        connectFirebase()
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
            .subscribe { t: List<Event>? ->
                if (t != null)
                    EventDataBase.getDataBase().eventDAO().insertEvents(t)
            }
    }

}