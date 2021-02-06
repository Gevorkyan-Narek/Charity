package com.cyclone.simbirsoftprobation.presentation.ui.dialog_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.databinding.HelpWithTextBinding
import moxy.MvpAppCompatDialogFragment

class HelpWithText : MvpAppCompatDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = HelpWithTextBinding.inflate(inflater, container, false)

        arguments?.getBoolean("isThings")?.let {
            binding.write.hint = getString(
                if (it)
                    R.string.what_thing_you_want_to_send
                else
                    R.string.what_help_you_can_do
            )
        }

        binding.dismissButton.setOnClickListener { dismiss() }
        binding.cancelButton.setOnClickListener { dismiss() }
        return binding.root
    }
}