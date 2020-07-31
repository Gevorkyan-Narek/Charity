package com.cyclone.simbirsoftprobation.json_helper

import android.content.Context
import com.cyclone.simbirsoftprobation.model.Event
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class JsonHelper(
    var context: Context
) {

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val eventsAdapter = moshi.adapter(Event::class.java)

    fun getEvents(): MutableList<Event> {
        val events = mutableListOf<Event>()
        for (i in 1..2) {
            val jsonEvent =
                context.assets.open("event$i.json").bufferedReader().use { it.readText() }
            val event = eventsAdapter.fromJson(jsonEvent)!!
            events.add(event)
        }
        return events
    }
}