package com.cyclone.simbirsoftprobation.presentation.ui.auth

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.presentation.presenter.AuthorizationPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.main_view.MainActivity
import com.jakewharton.rxbinding.widget.RxTextView
import kotlinx.android.synthetic.main.activity_authorization.*
import rx.Observable

class AuthorizationActivity : MvpAppCompatActivity(), AuthorizationView {

    companion object {
        const val MIN_LINE_LENGTH = 5
    }

    @InjectPresenter
    lateinit var authorizationPresenter: AuthorizationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
    }

    override fun setEnterOptions() {
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