package com.cyclone.simbirsoftprobation.View

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclone.simbirsoftprobation.Presenter.Datas
import com.cyclone.simbirsoftprobation.Presenter.PagerAdapter
import com.cyclone.simbirsoftprobation.Presenter.SearchResultsAdapter
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.search_object_fragment.*

class SearchResultFragment : Fragment(R.layout.search_object_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(PagerAdapter.ARG_OBJECT) }?.apply {
            search_results_recycler.layoutManager = LinearLayoutManager(view.context)
            search_results_recycler.adapter = SearchResultsAdapter(Datas.getResults())
        }
    }

    override fun onPause() {
        super.onPause()
        search_results_recycler.adapter = SearchResultsAdapter(Datas.getResults())
    }
}