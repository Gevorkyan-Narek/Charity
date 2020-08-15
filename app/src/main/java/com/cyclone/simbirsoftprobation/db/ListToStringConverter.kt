package com.cyclone.simbirsoftprobation.db

import androidx.room.TypeConverter

class ListToStringConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun fromList(photos: List<String>): String {
            return photos.joinToString(",")
        }

        @TypeConverter
        @JvmStatic
        fun toList(photo: String): List<String> {
            return photo.split(",")
        }

    }
}