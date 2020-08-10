package com.cyclone.simbirsoftprobation.Model

import android.graphics.Bitmap
import java.time.LocalDate

data class Person(
    val id: Int,
    var fullName: String,
    var date: LocalDate,
    var profession: String,
    var friends: MutableList<Person>,
    var icon: Bitmap?,
    var isPush: Boolean
)