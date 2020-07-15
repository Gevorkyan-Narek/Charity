package com.cyclone.simbirsoftprobation.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclone.simbirsoftprobation.Presenter.DataCreate
import com.cyclone.simbirsoftprobation.Presenter.PagerAdapter
import com.cyclone.simbirsoftprobation.Presenter.SearchResultsAdapter
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.search_object_fragment.*

class SearchResultFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_object_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(PagerAdapter.ARG_OBJECT) }?.apply {
            search_results_recycler.layoutManager = LinearLayoutManager(view.context)
            search_results_recycler.adapter = SearchResultsAdapter(DataCreate.getResults())
        }
    }

    override fun onPause() {
        super.onPause()
        search_results_recycler.adapter = SearchResultsAdapter(DataCreate.getResults())
    }
}