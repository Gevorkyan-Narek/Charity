package com.cyclone.simbirsoftprobation.domain.dagger

import android.app.Application
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.domain.dagger.components.*
import com.cyclone.simbirsoftprobation.domain.dagger.modules.GoogleSignInModule

class App : Application() {

    companion object {
        private lateinit var repositoryComponent: RepositoryComponents
        fun getComponent(): RepositoryComponents = repositoryComponent

        private lateinit var retrofitComponent: RetrofitComponent
        fun getRetrofitComponent(): RetrofitComponent = retrofitComponent

        private lateinit var authComponent: GoogleAuthComponent
        fun getAuthComponent(): GoogleAuthComponent = authComponent
    }

    override fun onCreate() {
        super.onCreate()
        repositoryComponent = buildComponent()
        retrofitComponent = buildRetrofitComponent()
        authComponent = buildAuthComponent()
    }

    private fun buildAuthComponent(): GoogleAuthComponent {
        return DaggerGoogleAuthComponent.builder().googleSignInModule(GoogleSignInModule(getString(R.string.default_web_client_id), applicationContext)).build()
    }

    private fun buildComponent(): RepositoryComponents {
        return DaggerRepositoryComponents.builder().build()
    }

    private fun buildRetrofitComponent(): RetrofitComponent {
        return DaggerRetrofitComponent.builder().build()
    }
}