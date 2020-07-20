package com.cyclone.simbirsoftprobation.Presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.item_filter.view.*

class FilterAdapter : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filterName: TextView = itemView.filter_name
        val switcher: Switch = itemView.filter_switch
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_filter, parent, false)
        return ViewHolder(itemView)
    }

    val filter = Datas.getInstance().filter
    override fun getItemCount(): Int = filter.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.filterName.text = filter[position].name
        holder.switcher.isChecked = filter[position].check
        holder.switcher.setOnCheckedChangeListener { buttonView, isChecked ->
            filter[position].check = isChecked
        }
    }
}