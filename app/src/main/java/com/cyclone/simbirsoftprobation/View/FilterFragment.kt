package com.cyclone.simbirsoftprobation.View

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclone.simbirsoftprobation.Presenter.FilterAdapter
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.filter_fragment.view.*

class FilterFragment : Fragment(R.layout.filter_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.filter_recycler.layoutManager = LinearLayoutManager(context)
        view.filter_recycler.adapter = FilterAdapter()
        view.accept.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        view.back.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }
}