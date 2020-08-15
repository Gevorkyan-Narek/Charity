package com.cyclone.simbirsoftprobation.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_of_help")
data class CategoryOfHelp (
    @PrimaryKey val id: String,
    val name_en: String,
    val name: String,
    val image: String
)