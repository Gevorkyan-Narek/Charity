package com.cyclone.simbirsoftprobation.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.news.NewsView

@InjectViewState
class NewsPresenter: MvpPresenter<NewsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.getEvents()
    }
}