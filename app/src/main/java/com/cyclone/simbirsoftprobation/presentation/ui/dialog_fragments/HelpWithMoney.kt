package com.cyclone.simbirsoftprobation.presentation.ui.dialog_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.cyclone.simbirsoftprobation.databinding.HelpWithMoneyBinding

class HelpWithMoney : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = HelpWithMoneyBinding.inflate(inflater, container, false)
        binding.dismissButton.setOnClickListener { dismiss() }
        binding.cancelButton.setOnClickListener { dismiss() }
        return binding.root
    }
}