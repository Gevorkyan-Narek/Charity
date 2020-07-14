package com.cyclone.simbirsoftprobation.Model

import android.graphics.Bitmap
import android.net.Uri
import java.io.Serializable
import java.time.LocalDate

data class Person(
    var id: Int,
    var fullName: String,
    var date: LocalDate,
    var profession: String,
    var friends: MutableList<Person>,
    var iconBitmap: Bitmap,
    var push: Boolean
): Serializable