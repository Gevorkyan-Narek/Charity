package com.cyclone.simbirsoftprobation.presentation.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cyclone.simbirsoftprobation.databinding.NewsFragmentBinding
import com.cyclone.simbirsoftprobation.domain.model.Event
import com.cyclone.simbirsoftprobation.domain.utilities.getFilteredEvents
import com.cyclone.simbirsoftprobation.presentation.presenter.NewsPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.main_view.MainActivity
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class NewsFragment : MvpAppCompatFragment(), NewsView {

    @InjectPresenter
    lateinit var newsPresenter: NewsPresenter
    private lateinit var binding: NewsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewsFragmentBinding.inflate(inflater, container, false)
        binding.filter.setOnClickListener { (activity as MainActivity).mainPresenter.switchToFilter() }
        return binding.root
    }

    override fun setEvents(events: List<Event>) {
        binding.newsRecycler.adapter = NewsAdapter(getFilteredEvents(events))
        binding.progressBarNews.visibility = View.GONE
    }

    override fun showEventsError() {
        Toast.makeText(context, "Ошибка получения новостей", Toast.LENGTH_SHORT).show()
    }
}