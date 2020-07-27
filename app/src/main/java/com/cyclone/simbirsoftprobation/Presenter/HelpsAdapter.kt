package com.cyclone.simbirsoftprobation.Presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cyclone.simbirsoftprobation.Model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.item_kind_of_help.view.*

class HelpsAdapter(private var categories: MutableList<CategoryOfHelp>): RecyclerView.Adapter<HelpsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imageHelp: ImageView = itemView.image_kind_of_help
        var nameHelp: TextView = itemView.name_kind_of_help
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_kind_of_help, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameHelp.text = categories[position].name
        holder.imageHelp.loadBitmap(holder.itemView.context, categories[position].image)
    }
}