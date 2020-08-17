package com.cyclone.simbirsoftprobation.presentation.ui.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.presentation.presenter.SearchResultPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.search.adapter.PagerAdapter
import com.cyclone.simbirsoftprobation.presentation.ui.search.adapter.SearchResultsAdapter
import com.cyclone.simbirsoftprobation.storage.Datas
import kotlinx.android.synthetic.main.search_object_fragment.*

class SearchResultFragment : MvpAppCompatFragment(), SearchResultView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_object_fragment, container, false)
    }

    @InjectPresenter
    lateinit var searchResultPresenter: SearchResultPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchResultPresenter.setAdapter(arguments?.containsKey(PagerAdapter.ARG_OBJECT) == true)
    }

    override fun setAdapter(key: Boolean) {
        if (key) {
            search_results_recycler.layoutManager = LinearLayoutManager(context)

            val mDividerItemDecoration = DividerItemDecoration(
                search_results_recycler.context,
                VERTICAL
            )

            search_results_recycler.addItemDecoration(mDividerItemDecoration)
            search_results_recycler.adapter =
                SearchResultsAdapter(
                    if (Datas.searchResults.isEmpty()) Datas.getSearchResultExamples() else Datas.searchResults
                )
        }
    }

    override fun update(isNotBlank: Boolean) {
        no_results_include.visibility = if (isNotBlank) View.GONE else View.VISIBLE
        search_results_recycler.adapter = SearchResultsAdapter(Datas.searchResults)
    }
}