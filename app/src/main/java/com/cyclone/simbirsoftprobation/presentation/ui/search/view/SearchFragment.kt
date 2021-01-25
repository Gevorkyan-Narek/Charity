package com.cyclone.simbirsoftprobation.presentation.ui.search.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.databinding.SearchFragmentBinding
import com.cyclone.simbirsoftprobation.domain.interactors.search_fragment.SearchViewInteractor
import com.cyclone.simbirsoftprobation.presentation.presenter.SearchPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.search.adapter.PagerAdapter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import rx.android.schedulers.AndroidSchedulers

class SearchFragment : MvpAppCompatFragment(), SearchView {

    @InjectPresenter
    lateinit var searchPresenter: SearchPresenter

    private lateinit var pagerAdapter: FragmentPagerAdapter
    private lateinit var binding: SearchFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setPager() {
        pagerAdapter = PagerAdapter(childFragmentManager)
        binding.pager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.pager)
    }

    override fun setSearchOptions() {
        setQueryTextChanges(binding.searchView)
            .observeOn(AndroidSchedulers.mainThread()).doOnNext {
                searchPresenter.updateResults(it.isNotBlank())
            }.subscribe()
    }

    override fun setSearchManager() {
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        binding.searchView.clearFocus()
        binding.searchView.isIconifiedByDefault = false
    }

    override fun updateResults(isNotBlank: Boolean) {
        val adapter = (binding.pager.adapter as PagerAdapter)
        adapter.updateResults(binding.pager.currentItem, isNotBlank)
    }

    private fun setQueryTextChanges(searchView: android.widget.SearchView) =
        SearchViewInteractor.setQueryTextChanges(searchView)
}