package com.cyclone.simbirsoftprobation.presentation.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.databinding.ItemSearchResultBinding

class SearchResultsAdapter(private var results: MutableList<String>) :
    RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemSearchResultBinding) : RecyclerView.ViewHolder(binding.root) {
        val resultName: TextView = binding.resultName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.resultName.text = results[position]
    }
}