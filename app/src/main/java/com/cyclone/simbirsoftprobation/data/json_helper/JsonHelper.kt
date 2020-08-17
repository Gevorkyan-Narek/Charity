package com.cyclone.simbirsoftprobation.data.json_helper

import android.content.Context
import com.cyclone.simbirsoftprobation.domain.model.Event
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class JsonHelper(
    var context: Context
) {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val type = Types.newParameterizedType(List::class.java, Event::class.java)
    private val eventsAdapter = moshi.adapter<List<Event>>(type)

    fun getEvents(): MutableList<Event> {
        val jsonEvent =
            context.assets.open("event.json").bufferedReader().use { it.readText() }
        val events = eventsAdapter.fromJson(jsonEvent)!!
        return events.toMutableList()
    }
}