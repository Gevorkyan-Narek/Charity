package com.cyclone.simbirsoftprobation.utilities

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cyclone.simbirsoftprobation.model.Event
import com.cyclone.simbirsoftprobation.model.Filter
import com.cyclone.simbirsoftprobation.storage.Datas
import com.cyclone.simbirsoftprobation.storage.Datas.Companion.checkOfRelevance
import com.cyclone.simbirsoftprobation.storage.Datas.Companion.remainingRelevance
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class MyUtils {
    companion object {
        fun filterNews(events: Event): Boolean {
            val filters = Datas.filter.filter { filter -> filter.check }
            for (filter: Filter in filters) {
                if (events.category == filter.id) return true
            }
            return false
        }

        fun getRelevance(events: Event): String {
            return if (checkOfRelevance(events.endDate)) {
                "Осталось ${remainingRelevance(events.endDate)} дней " +
                        "(${LocalDate.ofEpochDay(events.startDate).format(
                            DateTimeFormatter.ofPattern(
                                "dd.MM"
                            )
                        )} - " +
                        "${LocalDate.ofEpochDay(events.endDate)
                            .format(DateTimeFormatter.ofPattern("dd.MM"))})"
            } else {
                "${Datas.months[LocalDate.ofEpochDay(events.endDate).monthValue - 1]} " +
                        LocalDate.ofEpochDay(events.createAt)
                            .format(DateTimeFormatter.ofPattern("dd, yyyy"))
            }
        }
    }
}

fun ImageView.loadBitmap(
    context: Context,
    bitmap: Bitmap?,
    placeholderDrawable: Int? = null
) {
    if (placeholderDrawable != null)
        Glide.with(context)
            .load(bitmap)
            .placeholder(resources.getDrawable(placeholderDrawable, context.theme))
            .into(this)
    else
        Glide.with(context)
            .load(bitmap)
            .into(this)
}

fun ImageView.loadDrawable(
    context: Context,
    image: String,
    placeholderDrawable: Int? = null
) {

    val drawable = image.getDrawable(context)
    if (placeholderDrawable != null)
        Glide.with(context)
            .load(drawable)
            .placeholder(resources.getDrawable(placeholderDrawable, context.theme))
            .into(this)
    else
        Glide.with(context)
            .load(drawable)
            .into(this)
}

fun String.getDrawable(context: Context): Int {
    return context.resources.getIdentifier(this, "drawable", context.applicationContext.packageName)
}

fun getFilteredEvents(): MutableList<Event> {
    val events = Datas.events
    return if (Datas.filter.all { filter -> !filter.check }) events
    else events.filter { event ->
        MyUtils.filterNews(event)
    }.toMutableList()
}