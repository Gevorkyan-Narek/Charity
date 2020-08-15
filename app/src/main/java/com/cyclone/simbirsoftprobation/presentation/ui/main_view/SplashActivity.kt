package com.cyclone.simbirsoftprobation.presentation.ui.main_view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.presentation.ui.auth.AuthorizationActivity
import com.cyclone.simbirsoftprobation.dao.CategoryOfHelpDAO
import com.cyclone.simbirsoftprobation.dao.EventDAO
import com.cyclone.simbirsoftprobation.db.EventDataBase
import com.cyclone.simbirsoftprobation.domain.interactors.json_helper.JsonHelperAsync
import com.cyclone.simbirsoftprobation.domain.interactors.json_helper.JsonHelperCallback
import com.cyclone.simbirsoftprobation.domain.interactors.json_helper.JsonHelperExecutor
import com.cyclone.simbirsoftprobation.domain.interactors.json_helper.JsonHelperIntentService
import com.cyclone.simbirsoftprobation.domain.model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.domain.model.Event
import com.cyclone.simbirsoftprobation.domain.repository.category_of_help.CategoriesDataRepository
import com.cyclone.simbirsoftprobation.domain.repository.event.EventsDataRepository
import com.cyclone.simbirsoftprobation.domain.repository.network.RetrofitDataRepository
import com.cyclone.simbirsoftprobation.domain.repository.network.RetrofitInstance
import com.cyclone.simbirsoftprobation.storage.Datas
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SplashActivity : AppCompatActivity(R.layout.splash_screen) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EventDataBase.initDataBase(this)

        RetrofitDataRepository.getInstance().fillCategoriesDB()
        RetrofitDataRepository.getInstance().fillEventsDB()

        Handler().postDelayed({
            finish()
            startActivity(Intent(this, AuthorizationActivity::class.java))
        }, 1000L)
    }
}