package com.cyclone.simbirsoftprobation.domain.interactors.search_fragment

import android.widget.SearchView
import com.cyclone.simbirsoftprobation.domain.model.Event
import com.cyclone.simbirsoftprobation.domain.repository.event.EventsDataRepository
import com.cyclone.simbirsoftprobation.data.storage.Storage
import com.jakewharton.rxbinding.widget.RxSearchView
import rx.Observable
import rx.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchViewInteractor {

    companion object {

        fun setQueryTextChanges(searchView: SearchView): Observable<CharSequence> {
            return RxSearchView.queryTextChanges(searchView)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .doOnNext {
                    if (it.isNotBlank()) {
                        EventsDataRepository.getInstance().getEvents().doOnNext { t: List<Event> ->
                            Storage.searchResults = t.filter { event ->
                                event.name.toUpperCase(Locale.getDefault())
                                    .contains(it.toString().toUpperCase(Locale.getDefault()))
                            }.map { event -> event.name }.toMutableList()

                        }.subscribe()
                    }
                }
        }
    }
}