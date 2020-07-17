package com.cyclone.simbirsoftprobation.Presenter

import android.content.Context
import android.widget.Toast
import com.cyclone.simbirsoftprobation.Model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.Model.Event
import com.cyclone.simbirsoftprobation.Model.Filter
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate

class DiffUtils {
    companion object {
        fun checkOfRelevance(dateEnd: LocalDate): Boolean = dateEnd.isAfter(LocalDate.now())
        fun remainingRelevance(dateEnd: LocalDate): Int =
            dateEnd.dayOfYear - LocalDate.now().dayOfYear

        fun filterNews(events: Event): Boolean {
            val filters = Datas.getInstance().filter.filter { filter -> filter.check }
            for (event: CategoryOfHelp in events.categoryOfHelp) {
                for (filter: Filter in filters) {
                    if (event.name == filter.name) return true
                }
            }
            return false
        }
    }
}