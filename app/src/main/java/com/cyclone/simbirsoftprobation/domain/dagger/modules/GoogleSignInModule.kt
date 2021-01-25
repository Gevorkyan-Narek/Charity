package com.cyclone.simbirsoftprobation.domain.dagger.modules

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GoogleSignInModule(private val idToken: String, private val context: Context) {

    @Provides
    @Singleton
    fun googleSignInBuilder(): GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(idToken)
        .requestEmail()
        .build()

    @Provides
    @Singleton
    fun googleSignInClient(gso: GoogleSignInOptions): GoogleSignInClient = GoogleSignIn.getClient(context, gso)
}