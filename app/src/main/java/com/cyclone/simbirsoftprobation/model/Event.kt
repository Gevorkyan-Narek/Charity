package com.cyclone.simbirsoftprobation.model

import org.threeten.bp.LocalDate

data class Event(
//    val id: Int,
//    val title: String,
//    val shortDescription: String,
//    val dateStart: LocalDate,
//    val dateEnd: LocalDate,
//    val avatar: Int,
//    val categoryOfHelp: MutableList<String>,
//    val fullDescription: String,
//    val images: MutableList<Int>,
//    val address: String,
//    val tel: String,
//    val company: String

    val id: String,
    val name: String,
    val startDate: Long,
    val endDate: Long,
    val description: String,
    val status: Long,
    val photos: List<String>,
    val category: String,
    val createAt: Long,
    val phone: String,
    val address: String,
    val organisation: String
)