package com.cyclone.simbirsoftprobation.View

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.cyclone.simbirsoftprobation.Presenter.PagerAdapter
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_fragment.view.*

class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var pagerAdapter: FragmentPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_fragment, container, false)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        view.search_view.onActionViewExpanded()
        view.search_view.clearFocus()
        view.search_view.setIconifiedByDefault(false)
        view.search_view.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        view.search_view.setOnQueryTextListener(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pagerAdapter = PagerAdapter(childFragmentManager)
        pager.adapter = pagerAdapter
        tab_layout.setupWithViewPager(pager)
    }

    override fun onQueryTextSubmit(query: String?): Boolean = false

    override fun onQueryTextChange(newText: String?): Boolean = false
}