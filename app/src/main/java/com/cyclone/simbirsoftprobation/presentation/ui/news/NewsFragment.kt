package com.cyclone.simbirsoftprobation.presentation.ui.news

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.domain.model.Event
import com.cyclone.simbirsoftprobation.domain.utilities.getFilteredEvents
import com.cyclone.simbirsoftprobation.presentation.presenter.NewsPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.main_view.MainActivity
import kotlinx.android.synthetic.main.news_fragment.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class NewsFragment : MvpAppCompatFragment(R.layout.news_fragment), NewsView {

    @InjectPresenter
    lateinit var newsPresenter: NewsPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        filter.setOnClickListener { (activity as MainActivity).mainPresenter.switchToFilter() }
    }

    override fun setEvents(events: List<Event>) {
        news_recycler.adapter = NewsAdapter(getFilteredEvents(events))
        progressBarNews.visibility = View.GONE
    }

    override fun showEventsError() {
        Toast.makeText(context, "Ошибка получения новостей", Toast.LENGTH_SHORT).show()
    }
}