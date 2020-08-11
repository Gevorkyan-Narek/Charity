package com.cyclone.simbirsoftprobation.domain.interactors.search_fragment

import android.util.Log
import android.view.View
import android.widget.SearchView
import com.cyclone.simbirsoftprobation.data.json_helper.JsonHelper
import com.cyclone.simbirsoftprobation.domain.model.Event
import com.cyclone.simbirsoftprobation.domain.repository.event.EventsDataRepository
import com.cyclone.simbirsoftprobation.storage.Datas
import com.jakewharton.rxbinding.widget.RxSearchView
import kotlinx.android.synthetic.main.search_fragment.view.*
import rx.Observable
import rx.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class SearchViewInteractor {

    companion object {

        fun setQueryTextChanges(searchView: SearchView): Observable<CharSequence> {
            return RxSearchView.queryTextChanges(searchView)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .doOnNext {
                    if (it.isNotBlank()) {
                        EventsDataRepository.getInstance().getEvents().doOnNext { t: List<Event> ->
                            Datas.searchResults = t.filter { event ->
                                event.name.toUpperCase(Locale.getDefault())
                                    .contains(it.toString().toUpperCase(Locale.getDefault()))
                            }.map { event -> event.name }.toMutableList()

                        }.subscribe()
                    }
                }
        }
    }
}