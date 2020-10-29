package com.cyclone.simbirsoftprobation.presentation.presenter

import com.cyclone.simbirsoftprobation.domain.dagger.App
import com.cyclone.simbirsoftprobation.domain.repository.category_of_help.CategoriesDataRepository
import com.cyclone.simbirsoftprobation.domain.repository.network.RetrofitDataRepository
import com.cyclone.simbirsoftprobation.presentation.ui.category_of_help.CategoryOfHelpView
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class CategoryOfHelpPresenter : MvpPresenter<CategoryOfHelpView>() {

    @Inject
    lateinit var retrofitDataRepository: RetrofitDataRepository
    @Inject
    lateinit var categoriesDataRepository: CategoriesDataRepository

    init {
        App.getComponent().inject(this)
        updateCategories()
        getCategories()
    }

    private fun updateCategories() = retrofitDataRepository.fillCategoriesDB()

    private fun getCategories() {
        categoriesDataRepository
            .getCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { categories ->
                viewState.showCategories(categories)
            }
            .subscribe()
    }
}