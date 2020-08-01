package com.cyclone.simbirsoftprobation.search.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.json_helper.JsonHelper
import com.cyclone.simbirsoftprobation.search.adapter.PagerAdapter
import com.cyclone.simbirsoftprobation.storage.Datas
import com.jakewharton.rxbinding.widget.RxSearchView
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_fragment.view.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment(R.layout.search_fragment), SearchView.OnQueryTextListener {

    private lateinit var pagerAdapter: FragmentPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pagerAdapter = PagerAdapter(childFragmentManager)
        pager.adapter = pagerAdapter
        tab_layout.setupWithViewPager(pager)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        view.search_view.onActionViewExpanded()
        view.search_view.clearFocus()
        view.search_view.isIconifiedByDefault = false
        view.search_view.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        view.search_view.setOnQueryTextListener(this)

        RxSearchView.queryTextChanges(view.search_view).debounce(500, TimeUnit.MILLISECONDS)
            .subscribe {
                if (Datas.events.isEmpty()) {
                    Handler {
                        val executor = Executors.newSingleThreadExecutor()

                        executor.submit {
                            Datas.events = JsonHelper(context!!).getEvents()
                        }.get(5, TimeUnit.SECONDS)

                        executor.shutdown()

                        true

                    }.sendEmptyMessage(0)
                }

            }

    }

    override fun onQueryTextSubmit(query: String?): Boolean = false

    override fun onQueryTextChange(newText: String?): Boolean = false
}