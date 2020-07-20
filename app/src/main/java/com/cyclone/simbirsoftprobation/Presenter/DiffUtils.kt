package com.cyclone.simbirsoftprobation.Presenter

import com.cyclone.simbirsoftprobation.Model.Event
import com.cyclone.simbirsoftprobation.Model.Filter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class DiffUtils {
    companion object {
        fun checkOfRelevance(dateEnd: LocalDate): Boolean = dateEnd.isAfter(LocalDate.now())
        fun remainingRelevance(dateEnd: LocalDate): Int =
            dateEnd.dayOfYear - LocalDate.now().dayOfYear

        fun filterNews(events: Event): Boolean {
            val filters = Datas.getInstance().filter.filter { filter -> filter.check }
            for (event: String in events.categoryOfHelp) {
                for (filter: Filter in filters) {
                    if (event == filter.name) return true
                }
            }
            return false
        }

        fun getRelevance(events: Event): String {
            return if (checkOfRelevance(events.dateEnd)) {
                "Осталось ${remainingRelevance(events.dateEnd)} дней " +
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