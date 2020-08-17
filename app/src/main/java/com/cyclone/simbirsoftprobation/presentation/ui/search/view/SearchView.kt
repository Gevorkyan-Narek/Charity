package com.cyclone.simbirsoftprobation.presentation.ui.search.view

import com.arellomobile.mvp.MvpView

interface SearchView: MvpView {

    fun setPager()
    fun setSearchManager()
    fun setSearchOptions()
    fun updateResults(isNotBlank: Boolean)
}