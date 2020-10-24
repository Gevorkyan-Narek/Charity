package com.cyclone.simbirsoftprobation.presentation.presenter

import com.cyclone.simbirsoftprobation.domain.repository.category_of_help.CategoriesDataRepository
import com.cyclone.simbirsoftprobation.domain.repository.network.RetrofitDataRepository
import com.cyclone.simbirsoftprobation.presentation.ui.category_of_help.CategoryOfHelpView
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CategoryOfHelpPresenter:  MvpPresenter<CategoryOfHelpView>() {

    init {
        updateCategories()
        getCategories()
    }

    private fun updateCategories() {
        RetrofitDataRepository.getInstance().fillCategoriesDB()
    }

    private fun getCategories() {
        CategoriesDataRepository
            .getInstance()
            .getCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { categories ->
                viewState.showCategories(categories)
            }
            .subscribe()
    }
}