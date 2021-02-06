package com.cyclone.simbirsoftprobation.presentation.ui.category_of_help

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.databinding.ItemKindOfHelpBinding
import com.cyclone.simbirsoftprobation.domain.model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.domain.utilities.loadDrawable

class CategoryOfHelpAdapter(private var categories: List<CategoryOfHelp>) :
    RecyclerView.Adapter<CategoryOfHelpAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemKindOfHelpBinding) : RecyclerView.ViewHolder(binding.root) {
        var imageHelp: ImageView = binding.imageKindOfHelp
        var nameHelp: TextView = binding.nameKindOfHelp
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemKindOfHelpBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameHelp.text = categories[position].name
        holder.imageHelp.loadDrawable(holder.itemView.context, categories[position].image)
    }
}