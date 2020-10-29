package com.cyclone.simbirsoftprobation.domain.dagger.modules

import com.cyclone.simbirsoftprobation.data.db.EventDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DAOModule {

    @Provides
    @Singleton
    fun provideDatabase(): EventDataBase = EventDataBase.getDataBase()

    @Provides
    @Singleton
    fun eventDAO(eventDataBase: EventDataBase) = eventDataBase.eventDAO()

    @Provides
    @Singleton
    fun categoriesDAO(eventDataBase: EventDataBase) = eventDataBase.categoriesDAO()
}