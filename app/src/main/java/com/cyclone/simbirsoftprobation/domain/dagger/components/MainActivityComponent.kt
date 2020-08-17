package com.cyclone.simbirsoftprobation.domain.dagger.components

import com.cyclone.simbirsoftprobation.domain.dagger.modules.FilterFragmentModule
import com.cyclone.simbirsoftprobation.domain.dagger.modules.SearchModuleObject
import com.cyclone.simbirsoftprobation.presentation.ui.category_of_help.CategoryOfHelpFragment
import com.cyclone.simbirsoftprobation.presentation.ui.main_view.MainActivity
import com.cyclone.simbirsoftprobation.presentation.ui.news.NewsFragment
import com.cyclone.simbirsoftprobation.presentation.ui.profile.ProfileFragment
import com.cyclone.simbirsoftprobation.presentation.ui.search.view.SearchFragment
import dagger.Component

@Component (modules = [SearchModuleObject::class, FilterFragmentModule::class])
interface MainActivityComponent {

    fun getCategories(): CategoryOfHelpFragment
    fun getNews(): NewsFragment
    fun getProfile(): ProfileFragment
    fun getSearch(): SearchFragment

    fun inject(activity: MainActivity)
}