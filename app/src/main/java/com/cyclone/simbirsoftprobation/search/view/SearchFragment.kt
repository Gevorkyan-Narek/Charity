package com.cyclone.simbirsoftprobation.search.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.json_helper.JsonHelper
import com.cyclone.simbirsoftprobation.search.adapter.PagerAdapter
import com.cyclone.simbirsoftprobation.storage.Datas
import com.jakewharton.rxbinding.widget.RxSearchView
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_fragment.view.*
import kotlinx.android.synthetic.main.search_object_fragment.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

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

        RxSearchView.queryTextChanges(view.search_view)
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(Schedulers.io())
            .doOnNext {
                if (Datas.events.isEmpty()) Datas.events = JsonHelper(context!!).getEvents()

                if (it.isNotBlank()) {
                    Datas.searchResults = Datas.events.filter { event ->
                        event.title.toUpperCase(Locale.getDefault()).contains(
                            it.toString().toUpperCase(
                                Locale.getDefault()
                            )
                        )
                    }.map { event -> event.title }.toMutableList()
                }
            }
            .observeOn(AndroidSchedulers.mainThread()).doOnNext {
                val adapter = (pager.adapter as PagerAdapter)
                if (it.isNotBlank()) {
                    adapter.updateResults(pager.currentItem)
                    adapter.getRegisteredFragments(pager.currentItem).view!!.no_results_include.visibility = View.GONE
                } else adapter.getRegisteredFragments(pager.currentItem).view!!.no_results_include.visibility = View.VISIBLE
            }.subscribe()

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        view.search_view.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        view.search_view.clearFocus()
        view.search_view.isIconifiedByDefault = false
    }
}