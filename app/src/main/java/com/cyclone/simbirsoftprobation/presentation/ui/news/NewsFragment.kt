package com.cyclone.simbirsoftprobation.presentation.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.db.EventDataBase
import com.cyclone.simbirsoftprobation.domain.repository.event.EventsDataRepository
import com.cyclone.simbirsoftprobation.presentation.ui.filter.FilterFragment
import com.cyclone.simbirsoftprobation.domain.utilities.getFilteredEvents
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.news_fragment.view.*

class NewsFragment : Fragment(R.layout.news_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.news_recycler.layoutManager = LinearLayoutManager(context)

        EventsDataRepository
            .getInstance()
            .getEvents()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { events ->
                view.news_recycler.adapter = NewsAdapter(getFilteredEvents(events))
            }
            .doAfterNext {
                view.progressBarNews.visibility = View.GONE
            }
            .subscribe()

        view.filter.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.main_view_fragment,
                    FilterFragment()
                )?.addToBackStack("filter")
                ?.commit()
        }
    }
}