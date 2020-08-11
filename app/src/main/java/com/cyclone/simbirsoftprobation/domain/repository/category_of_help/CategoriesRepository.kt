package com.cyclone.simbirsoftprobation.domain.repository.category_of_help

import com.cyclone.simbirsoftprobation.domain.model.CategoryOfHelp
import io.reactivex.Observable

interface CategoriesRepository {

    fun getCategories(): Observable<List<CategoryOfHelp>>

    fun insertCategories(categoryOfHelp: CategoryOfHelp)

    fun insertCategories(categoriesOfHelp: List<CategoryOfHelp>)

    fun deleteCategories()
}