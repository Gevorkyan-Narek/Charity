package com.cyclone.simbirsoftprobation.json_helper

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.cyclone.simbirsoftprobation.db.EventDataBase
import com.cyclone.simbirsoftprobation.news.NewsAdapter

class JsonHelperIntentService : IntentService("BackgroundIntentService") {

    lateinit var adapter: NewsAdapter

    fun start(
        context: Context
    ) {
        val intentService = Intent(context, JsonHelperIntentService::class.java)
        context.startService(intentService)
    }

    override fun onHandleIntent(intent: Intent?) {
//        Thread.sleep(5000)
        val events = JsonHelper(this).getEvents()
        EventDataBase.getDataBase(this).eventDAO().insertEvents(events)
    }
}