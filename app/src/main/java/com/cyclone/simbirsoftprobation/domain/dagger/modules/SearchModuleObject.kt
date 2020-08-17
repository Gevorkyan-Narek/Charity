package com.cyclone.simbirsoftprobation.domain.dagger.modules

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cyclone.simbirsoftprobation.presentation.presenter.SearchViewPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.search.adapter.PagerAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object SearchModuleObject {

    @Provides
    @JvmStatic
    fun getSearchViewPresenter() = SearchViewPresenter()
}