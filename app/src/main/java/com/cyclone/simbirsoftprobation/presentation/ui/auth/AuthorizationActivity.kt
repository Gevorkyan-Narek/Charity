package com.cyclone.simbirsoftprobation.presentation.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.domain.dagger.App
import com.cyclone.simbirsoftprobation.presentation.presenter.AuthorizationPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.main_view.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_authorization.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import javax.inject.Inject

class AuthorizationActivity : MvpAppCompatActivity(R.layout.activity_authorization), AuthorizationView {

    companion object {
        private const val RC_SIGN_IN = 1
    }

    @InjectPresenter
    lateinit var authorizationPresenter: AuthorizationPresenter

    private lateinit var auth: FirebaseAuth
    @Inject
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enter.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        App.getAuthComponent().inject(this)

        auth = FirebaseAuth.getInstance()

        google.setOnClickListener { authorizationPresenter.signInGoogle() }
        authorizationPresenter.loginListener(input_email, input_password)
    }

    override fun buttonIsEnable(isEnable: Boolean) {
        enter.isEnabled = isEnable
        enter.setBackgroundColor(
            resources.getColor(
                if (isEnable) R.color.colorPrimaryGreen
                else R.color.dark_gray,
                theme
            )
        )
    }

    override fun signIn() {
        startActivityForResult(
            googleSignInClient.signInIntent,
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                task.getResult(ApiException::class.java)?.let {
                    firebaseAuthWithGoogle(it.idToken!!)
                }
            } catch (e: ApiException) {
                Log.d("AuthorizationActivity", "ActivityResult")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                      finish()
                } else {
                    Log.d("AuthorizationActivity", "AuthFailed")
                }
            }
    }
}