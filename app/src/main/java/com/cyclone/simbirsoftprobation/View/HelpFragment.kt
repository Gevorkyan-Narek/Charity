package com.cyclone.simbirsoftprobation.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.cyclone.simbirsoftprobation.Presenter.Datas
import com.cyclone.simbirsoftprobation.Presenter.HelpsAdapter
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.help_fragment.view.*

class HelpFragment : Fragment(R.layout.help_fragment) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)!!

        view.recycler_kind_of_help.layoutManager = GridLayoutManager(context, 2)
        view.recycler_kind_of_help.adapter = HelpsAdapter(Datas(resources).helps)

        return view
    }
}