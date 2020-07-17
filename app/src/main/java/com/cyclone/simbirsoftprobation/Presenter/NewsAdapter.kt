package com.cyclone.simbirsoftprobation.Presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cyclone.simbirsoftprobation.Model.Event
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.news_item.view.*
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.TextStyle
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(var events: MutableList<Event>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.news_avatar
        val title: TextView = itemView.news_title
        val content: TextView = itemView.content
        val date: TextView = itemView.date
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView).load(events[position].image).into(holder.image)
        holder.title.text = events[position].title
        holder.content.text = events[position].content
        if (DiffUtils.checkOfRelevance(events[position].dateEnd)) {
            holder.date.text =
                "Осталось ${DiffUtils.remainingRelevance(events[position].dateEnd)} дней " +
                        "(${events[position].dateStart.format(DateTimeFormatter.ofPattern("dd.MM"))} - " +
                        "${events[position].dateEnd.format(DateTimeFormatter.ofPattern("dd.MM"))})"
        } else {
            holder.date.text = "${Datas.months[events[position].dateEnd.monthValue - 1]} " +
                    "${events[position].dateEnd.format(DateTimeFormatter.ofPattern("dd, yyyy"))}"
        }
    }
}