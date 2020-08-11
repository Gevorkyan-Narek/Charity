package com.cyclone.simbirsoftprobation.presentation.presenter.search_fragment

import android.widget.SearchView
import com.cyclone.simbirsoftprobation.domain.interactors.search_fragment.SearchViewInteractor

class SearchViewPresenter {

    fun setQueryTextChanges(searchView: SearchView) = SearchViewInteractor.setQueryTextChanges(searchView)
}