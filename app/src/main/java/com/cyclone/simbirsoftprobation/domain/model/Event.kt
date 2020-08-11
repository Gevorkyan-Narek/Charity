package com.cyclone.simbirsoftprobation.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cyclone.simbirsoftprobation.data.db.ListToStringConverter

@Entity
data class Event(
    @PrimaryKey val id: String,
    val name: String,
    val startDate: Long,
    val endDate: Long,
    val shortDescription: String,
    val description: String,
    val status: Long,
    @TypeConverters(ListToStringConverter::class)
    val photos: List<String>,
    val category: String,
    val createAt: Long,
    val phone: String,
    val address: String,
    val organisation: String
)