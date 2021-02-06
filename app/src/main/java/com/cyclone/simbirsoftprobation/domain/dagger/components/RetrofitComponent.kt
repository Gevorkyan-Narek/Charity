package com.cyclone.simbirsoftprobation.domain.dagger.components

import com.cyclone.simbirsoftprobation.domain.dagger.modules.RetrofitModule
import com.cyclone.simbirsoftprobation.domain.repository.network.RetrofitDataRepository
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class])
@Singleton
interface RetrofitComponent {

    fun inject(retrofitDataRepository: RetrofitDataRepository)
}