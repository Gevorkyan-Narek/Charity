package com.cyclone.simbirsoftprobation.domain.dagger.modules

import com.cyclone.simbirsoftprobation.presentation.ui.filter.FilterFragment
import dagger.Module
import dagger.Provides

@Module
object FilterFragmentModule {

    @Provides
    @JvmStatic
    fun getFilter(): FilterFragment = FilterFragment()
}