package com.cyclone.simbirsoftprobation.presentation.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.presentation.presenter.FilterPresenter
import kotlinx.android.synthetic.main.filter_fragment.*
import kotlinx.android.synthetic.main.filter_fragment.view.*

class FilterFragment : MvpAppCompatFragment(), FilterView {

    @InjectPresenter
    lateinit var filterPresenter: FilterPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.filter_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.filter_recycler.layoutManager = LinearLayoutManager(context)
        view.accept.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        view.back.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    override fun getFilters() {
        filter_recycler.adapter = FilterAdapter()
    }
}