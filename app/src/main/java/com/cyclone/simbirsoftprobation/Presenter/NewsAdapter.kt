package com.cyclone.simbirsoftprobation.Presenter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cyclone.simbirsoftprobation.Model.Event
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.View.DetailActivity
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(events: MutableList<Event>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.news_avatar
        val title: TextView = itemView.news_title
        val content: TextView = itemView.content
        val date: TextView = itemView.date
    }

    var filteredEvents = if (Datas.getInstance().filter.all { filter -> !filter.check }) events
    else events.filter { event -> DiffUtils.filterNews(event) }.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = filteredEvents.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.loadDrawable(holder.itemView.context, filteredEvents[position].avatar)
        holder.title.text = filteredEvents[position].title
        holder.content.text = filteredEvents[position].shortDescription
        holder.date.text = DiffUtils.getRelevance(filteredEvents[position])
        holder.itemView.setOnClickListener {
            val detailActivity = Intent(it.context, DetailActivity::class.java)
            detailActivity.putExtra("event_id", filteredEvents[position].id)
            it.context.startActivity(detailActivity)
        }
    }
}