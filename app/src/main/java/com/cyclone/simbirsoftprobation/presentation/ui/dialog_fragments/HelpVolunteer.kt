package com.cyclone.simbirsoftprobation.presentation.ui.dialog_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.cyclone.simbirsoftprobation.databinding.HelpVolunteerBinding

class HelpVolunteer: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = HelpVolunteerBinding.inflate(inflater, container, false)

        binding.dismissButton.setOnClickListener { dismiss() }
        binding.cancelButton.setOnClickListener { dismiss() }

        return binding.root
    }
}