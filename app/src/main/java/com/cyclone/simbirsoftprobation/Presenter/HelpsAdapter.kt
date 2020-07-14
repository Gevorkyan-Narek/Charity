package com.cyclone.simbirsoftprobation.Presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cyclone.simbirsoftprobation.Model.Help
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.item_kind_of_help.view.*

class HelpsAdapter(private var helps: MutableList<Help>): RecyclerView.Adapter<HelpsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imageHelp = itemView.image_kind_of_help
        var nameHelp = itemView.name_kind_of_help
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_kind_of_help, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = helps.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameHelp.text = helps[position].name
        Glide.with(holder.itemView).load(helps[position].image).into(holder.imageHelp)
    }
}