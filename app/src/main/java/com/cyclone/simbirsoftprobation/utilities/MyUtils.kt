package com.cyclone.simbirsoftprobation.utilities

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cyclone.simbirsoftprobation.model.Event
import com.cyclone.simbirsoftprobation.model.Filter
import com.cyclone.simbirsoftprobation.storage.Datas
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class MyUtils {
    companion object {
        fun checkOfRelevance(dateEnd: LocalDate): Boolean = dateEnd.isAfter(LocalDate.now())
        fun remainingRelevance(dateEnd: LocalDate): Int =
            dateEnd.dayOfYear - LocalDate.now().dayOfYear

        fun filterNews(events: Event): Boolean {
            val filters = Datas.getInstance().filter.filter { filter -> filter.check }
            for (categoryOfHelp: String in events.categoryOfHelp) {
                for (filter: Filter in filters) {
                    if (categoryOfHelp == filter.name) return true
                }
            }
            return false
        }

        fun getRelevance(events: Event): String {
            return if (checkOfRelevance(
                    events.dateEnd
                )
            ) {
                "Осталось ${remainingRelevance(
                    events.dateEnd
                )} дней " +
                        "(${events.dateStart.format(
                            DateTimeFormatter.ofPattern(
                                "dd.MM"
                            )
                        )} - " +
                        "${events.dateEnd.format(DateTimeFormatter.ofPattern("dd.MM"))})"
            } else {
                "${Datas.months[events.dateEnd.monthValue - 1]} " +
                        events.dateEnd.format(DateTimeFormatter.ofPattern("dd, yyyy"))
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
    image: Int?,
    placeholderDrawable: Int? = null
) {
    if (placeholderDrawable != null)
        Glide.with(context)
            .load(image)
            .placeholder(resources.getDrawable(placeholderDrawable, context.theme))
            .into(this)
    else
        Glide.with(context)
            .load(image)
            .into(this)
}

fun getFilteredEvents(): MutableList<Event> {
    val events = Datas.events
    return if (Datas.getInstance().filter.all { filter -> !filter.check }) events
    else events.filter { event ->
        MyUtils.filterNews(
            event
        )
    }.toMutableList()
}