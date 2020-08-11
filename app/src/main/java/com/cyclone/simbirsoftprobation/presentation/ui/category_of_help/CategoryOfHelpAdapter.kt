package com.cyclone.simbirsoftprobation.presentation.ui.category_of_help

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.domain.model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.domain.utilities.loadDrawable
import kotlinx.android.synthetic.main.item_kind_of_help.view.*

class CategoryOfHelpAdapter(private var categories: MutableList<CategoryOfHelp>): RecyclerView.Adapter<CategoryOfHelpAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imageHelp: ImageView = itemView.image_kind_of_help
        var nameHelp: TextView = itemView.name_kind_of_help
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_kind_of_help, parent, false)
        return ViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameHelp.text = categories[position].name
        holder.imageHelp.loadDrawable(holder.itemView.context, categories[position].image)
    }
}