package com.cyclone.simbirsoftprobation.domain.dagger.modules

import com.cyclone.simbirsoftprobation.domain.repository.category_of_help.CategoriesDataRepository
import com.cyclone.simbirsoftprobation.domain.repository.event.EventsDataRepository
import com.cyclone.simbirsoftprobation.domain.repository.network.RetrofitDataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideEventsRepository() = EventsDataRepository()

    @Provides
    @Singleton
    fun provideCategoriesRepository() = CategoriesDataRepository()

    @Provides
    @Singleton
    fun provideRetrofitRepository() = RetrofitDataRepository()
}