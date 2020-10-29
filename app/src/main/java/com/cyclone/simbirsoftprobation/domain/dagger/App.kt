package com.cyclone.simbirsoftprobation.domain.dagger

import android.app.Application
import com.cyclone.simbirsoftprobation.domain.dagger.components.DaggerRepositoryComponents
import com.cyclone.simbirsoftprobation.domain.dagger.components.DaggerRetrofitComponent
import com.cyclone.simbirsoftprobation.domain.dagger.components.RepositoryComponents
import com.cyclone.simbirsoftprobation.domain.dagger.components.RetrofitComponent

class App : Application() {

    companion object {
        private lateinit var repositoryComponent: RepositoryComponents
        fun getComponent(): RepositoryComponents = repositoryComponent

        private lateinit var retrofitComponent: RetrofitComponent
        fun getRetrofitComponent(): RetrofitComponent = retrofitComponent
    }

    override fun onCreate() {
        super.onCreate()
        repositoryComponent = buildComponent()
        retrofitComponent = buildRetrofitComponent()
    }

    private fun buildComponent(): RepositoryComponents {
        return DaggerRepositoryComponents.builder().build()
    }

    private fun buildRetrofitComponent(): RetrofitComponent {
        return DaggerRetrofitComponent.builder().build()
    }
}