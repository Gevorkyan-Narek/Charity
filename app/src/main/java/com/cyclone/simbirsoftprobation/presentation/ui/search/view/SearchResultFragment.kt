package com.cyclone.simbirsoftprobation.presentation.ui.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclone.simbirsoftprobation.data.storage.Storage
import com.cyclone.simbirsoftprobation.databinding.SearchObjectFragmentBinding
import com.cyclone.simbirsoftprobation.presentation.presenter.SearchResultPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.search.adapter.PagerAdapter
import com.cyclone.simbirsoftprobation.presentation.ui.search.adapter.SearchResultsAdapter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class SearchResultFragment : MvpAppCompatFragment(), SearchResultView {

    @InjectPresenter
    lateinit var searchResultPresenter: SearchResultPresenter
    private lateinit var binding: SearchObjectFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchObjectFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchResultPresenter.setAdapter(arguments?.containsKey(PagerAdapter.ARG_OBJECT) == true)
    }

    override fun setAdapter(key: Boolean) {
        if (key) {
            binding.searchResultsRecycler.layoutManager = LinearLayoutManager(context)

            val mDividerItemDecoration = DividerItemDecoration(
                binding.searchResultsRecycler.context,
                VERTICAL
            )

            binding.searchResultsRecycler.addItemDecoration(mDividerItemDecoration)
            binding.searchResultsRecycler.adapter =
                SearchResultsAdapter(
                    if (Storage.searchResults.isEmpty()) Storage.getSearchResultExamples() else Storage.searchResults
                )
        }
    }

    override fun update(isNotBlank: Boolean) {
        binding.noResultsInclude.root.visibility = if (isNotBlank) View.GONE else View.VISIBLE
        binding.searchResultsRecycler.adapter = SearchResultsAdapter(Storage.searchResults)
    }
}