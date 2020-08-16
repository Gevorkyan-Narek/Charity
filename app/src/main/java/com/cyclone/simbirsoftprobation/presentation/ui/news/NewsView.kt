package com.cyclone.simbirsoftprobation.presentation.ui.news

import com.arellomobile.mvp.MvpView

interface NewsView: MvpView {

    fun getEvents()
    fun showFilter()
}