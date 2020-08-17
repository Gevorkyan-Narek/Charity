package com.cyclone.simbirsoftprobation.presentation.ui.main_view

import com.arellomobile.mvp.MvpView

interface SplashView : MvpView {

    fun downloadCategories()
    fun downloadEvents()
    fun init()
}