package com.cyclone.simbirsoftprobation.presentation.ui.search.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.presentation.presenter.SearchPresenter
import com.cyclone.simbirsoftprobation.presentation.presenter.SearchViewPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.search.adapter.PagerAdapter
import kotlinx.android.synthetic.main.search_fragment.*
import rx.android.schedulers.AndroidSchedulers

class SearchFragment : MvpAppCompatFragment(), SearchView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    @InjectPresenter
    lateinit var searchPresenter: SearchPresenter
    private lateinit var pagerAdapter: FragmentPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun setPager() {
        pagerAdapter = PagerAdapter(childFragmentManager)
        pager.adapter = pagerAdapter
        tab_layout.setupWithViewPager(pager)
    }

    override fun setSearchOptions() {
        SearchViewPresenter()
            .setQueryTextChanges(search_view)
            .observeOn(AndroidSchedulers.mainThread()).doOnNext {
                searchPresenter.updateResults(it.isNotBlank())
            }.subscribe()
    }

    override fun setSearchManager() {
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        search_view.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        search_view.clearFocus()
        search_view.isIconifiedByDefault = false
    }

    override fun updateResults(isNotBlank: Boolean) {
        val adapter = (pager.adapter as PagerAdapter)
        adapter.updateResults(pager.currentItem, isNotBlank)
    }
}