package com.cyclone.simbirsoftprobation.data.db

import androidx.room.TypeConverter
import java.util.stream.Collectors

class ListToStringConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun fromList(photos: List<String>): String {
            return photos.stream().collect(Collectors.joining(","))
        }

        @TypeConverter
        @JvmStatic
        fun toList(photo: String): List<String> {
            return photo.split(",")
        }

    }
}