package com.cyclone.simbirsoftprobation.presentation.ui.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cyclone.simbirsoftprobation.databinding.HistoryFragmentBinding
import com.cyclone.simbirsoftprobation.domain.dagger.App
import com.cyclone.simbirsoftprobation.domain.repository.event.EventsDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import moxy.MvpAppCompatFragment
import javax.inject.Inject

class HistoryFragment : MvpAppCompatFragment() {

    @Inject
    lateinit var eventsDataRepository: EventsDataRepository

    private var disp: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = HistoryFragmentBinding.inflate(inflater, container, false)

        disp = eventsDataRepository
            .getEvents()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                binding.recyclerHistory.adapter = HistoryAdapter(it)
            }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        disp = null
    }
}