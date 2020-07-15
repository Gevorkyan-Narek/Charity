package com.cyclone.simbirsoftprobation.View

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.cyclone.simbirsoftprobation.Presenter.PagerAdapter
import com.cyclone.simbirsoftprobation.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.search_fragment.view.*

class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var pagerAdapter: FragmentPagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    lateinit var searchView: androidx.appcompat.widget.SearchView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_fragment, container, false)
//        view.search_view.queryHint = "Введите название организации"
        searchView = view.search_view
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pagerAdapter = PagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = pagerAdapter
        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()
        super.onCreateOptionsMenu(menu, inflater)
        Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()
        menu.clear()
        inflater.inflate(R.menu.search, menu)
        searchView.queryHint = "Введите название организации"
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        menu.findItem(R.id.action_search).apply {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW)
            actionView = searchView
        }

//        searchView.setOnQueryTextListener(this)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}