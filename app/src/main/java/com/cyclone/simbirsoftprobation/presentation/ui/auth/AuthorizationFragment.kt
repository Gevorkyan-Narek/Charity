package com.cyclone.simbirsoftprobation.presentation.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.databinding.ActivityAuthorizationBinding
import com.cyclone.simbirsoftprobation.domain.dagger.App
import com.cyclone.simbirsoftprobation.presentation.presenter.AuthorizationPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.main_view.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import javax.inject.Inject

class AuthorizationFragment : MvpAppCompatFragment(), AuthorizationView {

    companion object {
        private const val RC_SIGN_IN = 1
    }

    @InjectPresenter
    lateinit var authorizationPresenter: AuthorizationPresenter
    private lateinit var auth: FirebaseAuth

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivityAuthorizationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.enter.setOnClickListener {
            (activity as MainActivity).mainPresenter.switchToCategories()
        }

        App.getAuthComponent().inject(this)

        auth = FirebaseAuth.getInstance()

        binding.google.setOnClickListener { authorizationPresenter.signInGoogle() }
        authorizationPresenter.loginListener(binding.inputEmail, binding.inputPassword)
    }

    override fun buttonIsEnable(isEnable: Boolean) {
        binding.enter.isEnabled = isEnable
        binding.enter.setBackgroundColor(
            resources.getColor(
                if (isEnable) R.color.colorPrimaryGreen
                else R.color.dark_gray,
                activity?.theme
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
                    (activity as MainActivity).mainPresenter.switchToCategories()
                } else {
                    Log.d("AuthorizationActivity", "AuthFailed")
                }
            }
    }
}