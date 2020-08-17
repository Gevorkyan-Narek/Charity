package com.cyclone.simbirsoftprobation.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.category_of_help.CategoryOfHelpView

@InjectViewState
class CategoryOfHelpPresenter:  MvpPresenter<CategoryOfHelpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showCategories()
    }
}