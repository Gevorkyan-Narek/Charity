package com.cyclone.simbirsoftprobation.presentation.ui.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.data.storage.Storage
import kotlinx.android.synthetic.main.item_filter.view.*

class FilterAdapter : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filterName: TextView = itemView.filter_name
        val switcher: SwitchCompat = itemView.filter_switch
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_filter, parent, false)
        return ViewHolder(itemView)
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