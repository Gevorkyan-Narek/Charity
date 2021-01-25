package com.cyclone.simbirsoftprobation.presentation.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cyclone.simbirsoftprobation.databinding.FilterFragmentBinding
import com.cyclone.simbirsoftprobation.presentation.presenter.FilterPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.main_view.MainActivity
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class FilterFragment : MvpAppCompatFragment(), FilterView {

    @InjectPresenter
    lateinit var filterPresenter: FilterPresenter
    private lateinit var binding: FilterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FilterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.accept.setOnClickListener {
            (activity as MainActivity).mainPresenter.switchToNews()
        }

        binding.back.setOnClickListener {
            (activity as MainActivity).mainPresenter.switchToNews()
        }

    }

    override fun getFilters() {
        binding.filterRecycler.adapter = FilterAdapter()
    }
}