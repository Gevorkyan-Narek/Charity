package com.cyclone.simbirsoftprobation.json_helper

import android.content.Context
import com.cyclone.simbirsoftprobation.model.Event
import com.google.gson.JsonArray
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.ParameterizedType

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