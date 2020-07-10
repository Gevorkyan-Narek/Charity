package com.cyclone.simbirsoftprobation.Model

import java.time.LocalDate

data class Person(
    var fullName: String,
    var date: LocalDate,
    var profession: String,
    var friends: MutableList<Person>,
    var iconID: Int,
    var push: Boolean
)