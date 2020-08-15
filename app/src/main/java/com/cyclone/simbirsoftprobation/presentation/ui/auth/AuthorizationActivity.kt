package com.cyclone.simbirsoftprobation.presentation.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.presentation.ui.main_view.MainActivity
import com.jakewharton.rxbinding.widget.RxTextView
import kotlinx.android.synthetic.main.activity_authorization.*
import rx.Observable

class AuthorizationActivity : AppCompatActivity(R.layout.activity_authorization) {

    companion object {
        const val MIN_LINE_LENGTH = 5
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Observable.combineLatest(
            RxTextView.textChanges(input_email),
            RxTextView.textChanges(input_password)
        ) { t1, t2 -> t1.length > MIN_LINE_LENGTH && t2.length > MIN_LINE_LENGTH }
            .doOnNext {
                enter.isEnabled = it
                enter.setBackgroundColor(
                    resources.getColor(
                        if (it) R.color.colorPrimaryGreen
                        else R.color.dark_gray,
                        theme
                    )
                )
            }
            .subscribe()

        enter.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}