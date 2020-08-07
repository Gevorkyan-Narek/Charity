package com.cyclone.simbirsoftprobation.dao

import androidx.room.*
import com.cyclone.simbirsoftprobation.model.Event
import io.reactivex.Flowable

@Dao
interface EventDAO {

    @Query("select * from event where id = :id")
    fun getEvent(id: String): Flowable<Event>

    @Query("select * from event order by createAt DESC")
    fun getEvents(): Flowable<List<Event>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertEvent(event: Event)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertEvents(events: List<Event>)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateEvent(event: Event)

    @Delete
    fun deleteEvent(event: Event)

    @Query("delete from event where id = :id")
    fun deleteEventById(id: String)

    @Query("delete from event")
    fun deleteAll()
}