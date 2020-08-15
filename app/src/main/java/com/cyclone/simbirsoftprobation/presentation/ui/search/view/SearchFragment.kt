package com.cyclone.simbirsoftprobation.presentation.ui.search.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.presentation.presenter.search_fragment.SearchViewPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.search.adapter.PagerAdapter
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_fragment.view.*
import rx.android.schedulers.AndroidSchedulers

class SearchFragment : Fragment(R.layout.search_fragment) {

    private lateinit var pagerAdapter: FragmentPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pagerAdapter = PagerAdapter(childFragmentManager)
        pager.adapter = pagerAdapter
        tab_layout.setupWithViewPager(pager)

        SearchViewPresenter().setQueryTextChanges(view.search_view)
            .observeOn(AndroidSchedulers.mainThread()).doOnNext {
                val adapter = (pager.adapter as PagerAdapter)
                adapter.updateResults(pager.currentItem, it.isNotBlank())
            }

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        view.search_view.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        view.search_view.clearFocus()
        view.search_view.isIconifiedByDefault = false
    }
}