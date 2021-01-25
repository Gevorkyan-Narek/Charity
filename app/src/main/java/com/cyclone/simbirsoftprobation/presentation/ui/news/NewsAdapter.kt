package com.cyclone.simbirsoftprobation.presentation.ui.news

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.databinding.ItemNewsBinding
import com.cyclone.simbirsoftprobation.domain.model.Event
import com.cyclone.simbirsoftprobation.domain.utilities.MyUtils
import com.cyclone.simbirsoftprobation.domain.utilities.loadDrawable

class NewsAdapter(private var filteredEvents: MutableList<Event>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.newsAvatar
        val title: TextView = binding.newsTitle
        val content: TextView = binding.content
        val date: TextView = binding.date
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = filteredEvents.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.loadDrawable(holder.itemView.context, filteredEvents[position].photos[0])
        holder.title.text = filteredEvents[position].name
        holder.content.text = filteredEvents[position].shortDescription
        holder.date.text = MyUtils.getRelevance(filteredEvents[position])
        holder.itemView.setOnClickListener {
            val detailActivity = Intent(it.context, DetailActivity::class.java)
            detailActivity.putExtra("event_id", filteredEvents[position].id)
            it.context.startActivity(detailActivity)
        }
    }
}