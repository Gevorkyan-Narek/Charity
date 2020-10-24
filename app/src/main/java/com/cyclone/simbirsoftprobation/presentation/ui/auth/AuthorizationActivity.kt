package com.cyclone.simbirsoftprobation.presentation.ui.auth

import android.content.Intent
import android.os.Bundle
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.presentation.presenter.AuthorizationPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.main_view.MainActivity
import kotlinx.android.synthetic.main.activity_authorization.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class AuthorizationActivity : MvpAppCompatActivity(R.layout.activity_authorization), AuthorizationView {

    @InjectPresenter
    lateinit var authorizationPresenter: AuthorizationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enter.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

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
}