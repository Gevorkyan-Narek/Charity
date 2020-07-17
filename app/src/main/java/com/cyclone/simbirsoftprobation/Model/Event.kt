package com.cyclone.simbirsoftprobation.Model

import android.graphics.Bitmap
import org.threeten.bp.LocalDate

data class Event(
    val id: Int,
    val title: String,
    val content: String,
    val dateStart: LocalDate,
    val dateEnd: LocalDate,
    val image: Bitmap,
    val categoryOfHelp: MutableList<CategoryOfHelp>
)