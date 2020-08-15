package com.cyclone.simbirsoftprobation.search.view

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.search.adapter.PagerAdapter
import com.cyclone.simbirsoftprobation.search.adapter.SearchResultsAdapter
import com.cyclone.simbirsoftprobation.storage.Datas
import kotlinx.android.synthetic.main.search_object_fragment.*


class SearchResultFragment : Fragment(R.layout.search_object_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (arguments?.containsKey(PagerAdapter.ARG_OBJECT) == true) {
            search_results_recycler.layoutManager = LinearLayoutManager(view.context)

            val mDividerItemDecoration = DividerItemDecoration(
                search_results_recycler.context,
                VERTICAL
            )

            search_results_recycler.addItemDecoration(mDividerItemDecoration)
            search_results_recycler.adapter =
                SearchResultsAdapter(if (Datas.searchResults.isEmpty()) Datas.getSearchResultExamples() else Datas.searchResults)
        }
    }

    fun update(isNotBlank: Boolean) {
        no_results_include.visibility = if (isNotBlank) View.GONE else View.VISIBLE
        search_results_recycler.adapter = SearchResultsAdapter(Datas.searchResults)
    }
}