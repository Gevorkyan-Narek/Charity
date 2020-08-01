package com.cyclone.simbirsoftprobation.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.main_view.MainActivity
import com.jakewharton.rxbinding.widget.RxTextView
import kotlinx.android.synthetic.main.activity_authorization.*
import rx.Observable

class AuthorizationActivity : AppCompatActivity(R.layout.activity_authorization) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Observable.combineLatest(
            RxTextView.textChanges(input_email),
            RxTextView.textChanges(input_password)
        ) { t1, t2 -> t1.length > 5 && t2.length > 5 }
            .subscribe {
                if (it) {
                    enter.isEnabled = true
                    enter.setBackgroundColor(
                        resources.getColor(
                            R.color.colorPrimaryGreen,
                            theme
                        )
                    )
                } else {
                    enter.isEnabled = false
                    enter.setBackgroundColor(
                        resources.getColor(
                            R.color.dark_gray,
                            theme
                        )
                    )
                }
            }

        enter.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}