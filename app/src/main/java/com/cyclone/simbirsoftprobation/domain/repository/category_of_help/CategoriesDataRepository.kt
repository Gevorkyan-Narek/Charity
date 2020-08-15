package com.cyclone.simbirsoftprobation.domain.repository.category_of_help

import android.util.Log
import com.cyclone.simbirsoftprobation.db.EventDataBase
import com.cyclone.simbirsoftprobation.domain.model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.domain.model.Filter
import com.cyclone.simbirsoftprobation.storage.Datas
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class CategoriesDataRepository : CategoriesRepository {

    companion object {
        private val instance = CategoriesDataRepository()

        fun getInstance(): CategoriesDataRepository = instance
    }

    override fun getCategories(): Observable<List<CategoryOfHelp>> {
        return EventDataBase
            .getDataBase()
            .categoriesDAO()
            .getCategories()
            .doOnNext {
                Datas.filter = it.map {
                    Filter(
                        it.id,
                        it.name
                    )
                }.toMutableList()
            }
            .subscribeOn(Schedulers.io())
            .toObservable()
    }

    override fun insertCategories(categoryOfHelp: CategoryOfHelp) {
        EventDataBase.getDataBase().categoriesDAO().insertCategories(categoryOfHelp)
    }

    override fun insertCategories(categoriesOfHelp: List<CategoryOfHelp>) {
        EventDataBase.getDataBase().categoriesDAO().insertCategories(categoriesOfHelp)
    }

    override fun deleteCategories() {
        Observable.fromCallable {
            EventDataBase
                .getDataBase()
                .categoriesDAO()
        }.subscribeOn(Schedulers.io())
            .doOnNext { t ->
                t.deleteCategories()
                Log.d("Categories", "Deleted")
            }
            .subscribe()
    }
}