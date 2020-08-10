package com.cyclone.simbirsoftprobation.model

data class Event(
    val id: String,
    val name: String,
    val startDate: Long,
    val endDate: Long,
    val shortDescription: String,
    val description: String,
    val status: Long,
    val photos: List<String>,
    val category: String,
    val createAt: Long,
    val phone: String,
    val address: String,
    val organisation: String
)