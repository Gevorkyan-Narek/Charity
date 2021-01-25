package com.cyclone.simbirsoftprobation.domain.dagger.components

import com.cyclone.simbirsoftprobation.domain.dagger.modules.GoogleSignInModule
import com.cyclone.simbirsoftprobation.presentation.ui.auth.AuthorizationActivity
import dagger.Component
import javax.inject.Singleton

@Component (modules = [GoogleSignInModule::class])
@Singleton
interface GoogleAuthComponent {

    fun inject(authorizationActivity: AuthorizationActivity)
}