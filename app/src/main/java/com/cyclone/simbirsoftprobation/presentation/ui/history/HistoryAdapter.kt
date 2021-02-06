package com.cyclone.simbirsoftprobation.presentation.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.databinding.ItemHistoryBinding
import com.cyclone.simbirsoftprobation.domain.model.Event
import com.cyclone.simbirsoftprobation.domain.utilities.MyUtils

class HistoryAdapter(private var list: List<Event>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.name
        val date = binding.date
        val type = binding.helpType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.type.text = "Помощь деньгами"
        holder.date.text = MyUtils.getRelevance(list[position])
    }

    override fun getItemCount(): Int = list.size
}