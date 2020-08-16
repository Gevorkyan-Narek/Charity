package com.cyclone.simbirsoftprobation.presentation.ui.search.view

import com.arellomobile.mvp.MvpView

interface SearchResultView: MvpView {

    fun setAdapter(key: Boolean)
    fun update(isNotBlank: Boolean)
}