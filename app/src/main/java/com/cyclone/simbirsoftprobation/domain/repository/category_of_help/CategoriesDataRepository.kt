package com.cyclone.simbirsoftprobation.domain.repository.category_of_help

import com.cyclone.simbirsoftprobation.data.dao.CategoryOfHelpDAO
import com.cyclone.simbirsoftprobation.data.db.EventDataBase
import com.cyclone.simbirsoftprobation.data.storage.Storage
import com.cyclone.simbirsoftprobation.domain.dagger.App
import com.cyclone.simbirsoftprobation.domain.model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.domain.model.Filter
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoriesDataRepository @Inject constructor() : CategoriesRepository {

    @Inject
    lateinit var categoriesDAO: CategoryOfHelpDAO

    init {
        App.getComponent().inject(this)
    }

    override fun getCategories(): Observable<List<CategoryOfHelp>> {
        return categoriesDAO
            .getCategories()
            .doOnNext {
                Storage.filter = it.map { category ->
                    Filter(
                        category.id,
                        category.name
                    )
                }.toMutableList()
            }
            .subscribeOn(Schedulers.io())
            .toObservable()
    }

    override fun insertCategories(categoryOfHelp: CategoryOfHelp) {
        categoriesDAO.insertCategories(categoryOfHelp)
    }

    override fun insertCategories(categoriesOfHelp: List<CategoryOfHelp>) {
        categoriesDAO.insertCategories(categoriesOfHelp)
    }

    override fun deleteCategories() {
        Observable.fromCallable { categoriesDAO }
            .subscribeOn(Schedulers.io())
            .doOnNext { t ->
                t.deleteCategories()
            }
            .subscribe()
    }
}