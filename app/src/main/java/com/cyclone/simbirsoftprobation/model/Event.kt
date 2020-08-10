package com.cyclone.simbirsoftprobation.model

import org.threeten.bp.LocalDate

data class Event(
    val id: Int,
    val title: String,
    val shortDescription: String,
    val dateStart: LocalDate,
    val dateEnd: LocalDate,
    val avatar: Int,
    val categoryOfHelp: MutableList<String>,
    val fullDescription: String,
    val images: MutableList<Int>,
    val address: String,
    val tel: String,
    val company: String
)