package com.cyclone.simbirsoftprobation.presentation.ui.main_view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.data.db.EventDataBase
import com.cyclone.simbirsoftprobation.domain.repository.network.RetrofitDataRepository
import com.cyclone.simbirsoftprobation.presentation.ui.auth.AuthorizationActivity
import com.cyclone.simbirsoftprobation.data.storage.Storage
import com.cyclone.simbirsoftprobation.domain.dagger.App
import com.jakewharton.threetenabp.AndroidThreeTen
import moxy.MvpAppCompatActivity
import javax.inject.Inject

class SplashActivity : MvpAppCompatActivity(R.layout.splash_screen) {

    @Inject
    lateinit var retrofitDataRepository: RetrofitDataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        startMain()
        App.getComponent().inject(this)
    }

    private fun init() {
        EventDataBase.initDataBase(this)
        AndroidThreeTen.init(this)
        Storage.newInstance(resources)
    }

    private fun startMain() {
        Handler().postDelayed({
            finish()
            startActivity(Intent(this, AuthorizationActivity::class.java))
        }, 1000L)
    }
}