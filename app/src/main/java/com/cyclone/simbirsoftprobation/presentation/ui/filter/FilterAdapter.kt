package com.cyclone.simbirsoftprobation.presentation.ui.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.data.storage.Storage
import com.cyclone.simbirsoftprobation.databinding.ItemFilterBinding

class FilterAdapter : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemFilterBinding) : RecyclerView.ViewHolder(binding.root) {
        val filterName: TextView = binding.filterName
        val switcher: SwitchCompat = binding.filterSwitch
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = Storage.filter.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.filterName.text = Storage.filter[position].name
        holder.switcher.isChecked = Storage.filter[position].check
        holder.switcher.setOnCheckedChangeListener { _, isChecked ->
            Storage.filter[position].check = isChecked
        }
    }
}