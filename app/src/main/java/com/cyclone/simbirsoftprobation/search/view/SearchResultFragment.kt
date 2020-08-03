package com.cyclone.simbirsoftprobation.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclone.simbirsoftprobation.storage.Datas
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.search.adapter.PagerAdapter
import com.cyclone.simbirsoftprobation.search.adapter.SearchResultsAdapter
import kotlinx.android.synthetic.main.search_object_fragment.*

class SearchResultFragment : Fragment(R.layout.search_object_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (arguments?.containsKey(PagerAdapter.ARG_OBJECT)!!) {
            search_results_recycler.layoutManager = LinearLayoutManager(view.context)
            search_results_recycler.adapter =
                SearchResultsAdapter(if (Datas.searchResults.isEmpty()) Datas.getSearchResultExamples() else Datas.searchResults)
        }
    }

    fun update() {
        search_results_recycler.adapter = SearchResultsAdapter(Datas.searchResults)
    }
}