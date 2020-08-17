package com.cyclone.simbirsoftprobation.presentation.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.item_search_result.view.*

class SearchResultsAdapter(var results: MutableList<String>) :
    RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val resultName: TextView = itemView.result_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent, false)
        return ViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.resultName.text = results[position]
    }
}