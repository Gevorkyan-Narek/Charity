package com.cyclone.simbirsoftprobation.domain.model

import android.graphics.Bitmap
import org.threeten.bp.LocalDate

data class Person(
    val id: Int,
    var fullName: String,
    var date: LocalDate,
    var profession: String,
    var friends: MutableList<Person>,
    var icon: Bitmap?,
    var isPush: Boolean
)