package com.cyclone.simbirsoftprobation.presentation.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.domain.repository.event.EventsDataRepository
import com.cyclone.simbirsoftprobation.domain.utilities.getFilteredEvents
import com.cyclone.simbirsoftprobation.presentation.presenter.NewsPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.category_of_help.CategoryOfHelpFragment
import com.cyclone.simbirsoftprobation.presentation.ui.filter.FilterFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.news_fragment.*
import kotlinx.android.synthetic.main.news_fragment.view.*
import javax.inject.Inject

class NewsFragment @Inject constructor() : MvpAppCompatFragment(), NewsView {

    @InjectPresenter
    lateinit var newsPresenter: NewsPresenter

    @Inject
    lateinit var filterFragment: FilterFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        filter.setOnClickListener { showFilter() }
        view.news_recycler.layoutManager = LinearLayoutManager(context)
    }

    override fun getEvents() {
        EventsDataRepository
            .getInstance()
            .getEvents()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { events ->
                news_recycler.adapter = NewsAdapter(getFilteredEvents(events))
            }
            .doAfterNext {
                progressBarNews.visibility = View.GONE
            }
            .subscribe()
    }

    override fun showFilter() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(
                R.id.main_view_fragment,
                filterFragment
            )?.addToBackStack("filter")
            ?.commit()
    }
}