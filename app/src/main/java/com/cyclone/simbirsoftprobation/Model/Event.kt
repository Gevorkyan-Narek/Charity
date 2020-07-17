package com.cyclone.simbirsoftprobation.Model

import android.graphics.Bitmap
import org.threeten.bp.LocalDate

data class Event(
    val id: Int,
    val title: String,
    val shortDescription: String,
    val dateStart: LocalDate,
    val dateEnd: LocalDate,
    val avatar: Bitmap,
    val categoryOfHelp: MutableList<CategoryOfHelp>,
    val fullDescription: String,
    val images: MutableList<Bitmap>,
    val address: String,
    val tel: String
)