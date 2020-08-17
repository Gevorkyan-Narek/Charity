package com.cyclone.simbirsoftprobation.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cyclone.simbirsoftprobation.dao.CategoryOfHelpDAO
import com.cyclone.simbirsoftprobation.dao.EventDAO
import com.cyclone.simbirsoftprobation.data.db.ListToStringConverter
import com.cyclone.simbirsoftprobation.domain.model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.domain.model.Event

@Database(entities = [CategoryOfHelp::class, Event::class], version = 2, exportSchema = false)
@TypeConverters(ListToStringConverter::class)
abstract class EventDataBase : RoomDatabase() {

    abstract fun eventDAO(): EventDAO
    abstract fun categoriesDAO(): CategoryOfHelpDAO

    companion object {
        private var INSTANCE: EventDataBase? = null

        fun initDataBase(context: Context) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                EventDataBase::class.java,
                "event"
            ).build()

            INSTANCE = instance
        }

        fun getDataBase(): EventDataBase = INSTANCE!!
    }
}