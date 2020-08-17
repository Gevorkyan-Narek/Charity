package com.cyclone.simbirsoftprobation.presentation.ui.main_view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.db.EventDataBase
import com.cyclone.simbirsoftprobation.domain.repository.network.RetrofitDataRepository
import com.cyclone.simbirsoftprobation.presentation.presenter.SplashPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.auth.AuthorizationActivity
import com.cyclone.simbirsoftprobation.storage.Datas
import com.jakewharton.threetenabp.AndroidThreeTen

class SplashActivity : MvpAppCompatActivity(), SplashView {

    @InjectPresenter
    lateinit var splashPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler().postDelayed({
            finish()
            startActivity(Intent(this, AuthorizationActivity::class.java))
        }, 1000L)
    }

    override fun init() {
        EventDataBase.initDataBase(this)
        AndroidThreeTen.init(this)
        Datas.newInstance(resources)
    }

    override fun downloadCategories() {
        RetrofitDataRepository.getInstance().fillCategoriesDB()
    }

    override fun downloadEvents() {
        RetrofitDataRepository.getInstance().fillEventsDB()
    }
}