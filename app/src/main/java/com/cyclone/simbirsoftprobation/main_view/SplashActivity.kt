package com.cyclone.simbirsoftprobation.main_view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.auth.AuthorizationActivity

class SplashActivity : AppCompatActivity(R.layout.splash_screen) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            finish()
            startActivity(Intent(this, AuthorizationActivity::class.java))
        }, 1000L)
    }
}