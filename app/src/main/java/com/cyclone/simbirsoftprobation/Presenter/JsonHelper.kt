package com.cyclone.simbirsoftprobation.Presenter

import android.content.res.AssetManager
import com.cyclone.simbirsoftprobation.Model.Event
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class JsonHelper(var assets: AssetManager) {

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val eventsAdapter = moshi.adapter(Event::class.java)

    fun getEvents(): MutableList<Event> {
        val events = mutableListOf<Event>()
        for (i in 1..2) {
            val jsonEvent =
                assets.open("event$i.json").bufferedReader().use { it.readText() }
            val event = eventsAdapter.fromJson(jsonEvent)!!
            events.add(event)
        }
        return events
    }
}