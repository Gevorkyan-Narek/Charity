package com.cyclone.simbirsoftprobation.main_view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.auth.AuthorizationActivity
import com.cyclone.simbirsoftprobation.db.EventDataBase
import com.cyclone.simbirsoftprobation.json_helper.JsonHelperCallback
import com.cyclone.simbirsoftprobation.json_helper.JsonHelperExecutor
import com.cyclone.simbirsoftprobation.model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.model.Event
import com.cyclone.simbirsoftprobation.network.RetrofitInstance
import com.cyclone.simbirsoftprobation.storage.Datas
import com.cyclone.simbirsoftprobation.utilities.connectFirebase
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SplashActivity : AppCompatActivity(R.layout.splash_screen),
    JsonHelperCallback<MutableList<Event>> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = EventDataBase.getDataBase(this)

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
                JsonHelperExecutor().submit(this, this)

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