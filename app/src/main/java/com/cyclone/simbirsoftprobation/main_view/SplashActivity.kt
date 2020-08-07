package com.cyclone.simbirsoftprobation.main_view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.auth.AuthorizationActivity
import com.cyclone.simbirsoftprobation.dao.CategoryOfHelpDAO
import com.cyclone.simbirsoftprobation.dao.EventDAO
import com.cyclone.simbirsoftprobation.db.EventDataBase
import com.cyclone.simbirsoftprobation.json_helper.JsonHelperAsync
import com.cyclone.simbirsoftprobation.json_helper.JsonHelperCallback
import com.cyclone.simbirsoftprobation.json_helper.JsonHelperExecutor
import com.cyclone.simbirsoftprobation.json_helper.JsonHelperIntentService
import com.cyclone.simbirsoftprobation.model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.model.Event
import com.cyclone.simbirsoftprobation.network.RetrofitInstance
import com.cyclone.simbirsoftprobation.storage.Datas
import com.cyclone.simbirsoftprobation.utilities.connectFirebase
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SplashActivity : AppCompatActivity(R.layout.splash_screen),
    JsonHelperCallback<MutableList<Event>> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = EventDataBase.getDataBase(this)

        Observable.just(db.eventDAO())
            .subscribeOn(Schedulers.io())
            .subscribe { t: EventDAO? ->
                Log.d("EventDAO", "Deleted")
                t?.deleteAll()
            }
        Observable.just(db.categoriesDAO())
            .subscribeOn(Schedulers.io())
            .subscribe { t: CategoryOfHelpDAO? ->
                Log.d("CategoriesDAO", "Deleted")
                t?.deleteCategories()
            }


        RetrofitInstance.getInstance()
            .connectFirebase()
            .getCategories()
            .onErrorReturn {
                Datas.categoriesOfHelp
            }
            .subscribe { t: List<CategoryOfHelp>? ->
                db.categoriesDAO().insertCategories(t!!)
            }


        RetrofitInstance.getInstance()
            .connectFirebase()
            .getEvents()
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                Log.d("ErrorReturn", "ErrorReturn")
                // Async
//                JsonHelperAsync(this, this).execute()

//                     Executor
                JsonHelperExecutor().submit(this, this)

//                     IntentService
//                JsonHelperIntentService().start(this)

                null
            }
            .observeOn(Schedulers.io())
            .subscribe { t: List<Event>? ->
                if (t != null)
                    db.eventDAO().insertEvents(t)
            }

        Handler().postDelayed({
            finish()
            startActivity(Intent(this, AuthorizationActivity::class.java))
        }, 1000L)
    }

    override fun onFailure(e: Exception) {
        e.printStackTrace()
    }
}