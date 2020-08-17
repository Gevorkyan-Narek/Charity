package com.cyclone.simbirsoftprobation.presentation.presenter

import android.widget.SearchView
import com.cyclone.simbirsoftprobation.domain.interactors.search_fragment.SearchViewInteractor
import javax.inject.Inject

class SearchViewPresenter @Inject constructor() {

    fun setQueryTextChanges(searchView: SearchView) = SearchViewInteractor.setQueryTextChanges(searchView)
}