package com.cyclone.simbirsoftprobation.Presenter

import android.content.res.AssetManager
import android.os.AsyncTask
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.Model.Event
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class JsonHelperAsync(
    var assets: AssetManager,
    var newsRecycler: RecyclerView
) : AsyncTask<Void, Int, MutableList<Event>>() {

//    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
//    val eventsAdapter = moshi.adapter(Event::class.java)

//    fun getEvents(): MutableList<Event> {
//        val events = mutableListOf<Event>()
//        for (i in 1..2) {
//            val jsonEvent =
//                assets.open("event$i.json").bufferedReader().use { it.readText() }
//            val event = eventsAdapter.fromJson(jsonEvent)!!
//            events.add(event)
//        }
//        return events
//    }

    override fun doInBackground(vararg params: Void): MutableList<Event>? {
//        Log.d("AsyncTask", "Start")
//        val events = mutableListOf<Event>()
//        for (i in 1..2) {
//            Log.d("AsyncTask", i.toString())
//            val jsonEvent = assets.open("event$i.json").bufferedReader().use { it.readText() }
//            events.add(eventsAdapter.fromJson(jsonEvent)!!)
//        }
//        Log.d("AsyncTask", "End")
        return JsonHelper(assets).getEvents()
    }

    override fun onPostExecute(result: MutableList<Event>?) {
        Datas.events = result!!
        newsRecycler.adapter = NewsAdapter(result)
    }
}