package com.cyclone.simbirsoftprobation.presentation.ui.filter

import android.os.Bundle
import android.view.View
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.presentation.presenter.FilterPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.main_view.MainActivity
import kotlinx.android.synthetic.main.filter_fragment.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class FilterFragment : MvpAppCompatFragment(R.layout.filter_fragment), FilterView {

    @InjectPresenter
    lateinit var filterPresenter: FilterPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        accept.setOnClickListener {
            (activity as MainActivity).mainPresenter.switchToNews()
        }

        back.setOnClickListener {
            (activity as MainActivity).mainPresenter.switchToNews()
        }

    }

    override fun getFilters() {
        filter_recycler.adapter = FilterAdapter()
    }
}