package com.cyclone.simbirsoftprobation.Presenter

import org.threeten.bp.Instant
import org.threeten.bp.LocalDate

class DiffUtils {
    companion object {
        fun checkOfRelevance(dateEnd: LocalDate): Boolean = dateEnd.isAfter(LocalDate.now())
        fun remainingRelevance(dateEnd: LocalDate): Int = dateEnd.dayOfYear - LocalDate.now().dayOfYear
    }
}