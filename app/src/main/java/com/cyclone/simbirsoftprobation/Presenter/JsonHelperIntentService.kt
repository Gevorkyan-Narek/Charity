package com.cyclone.simbirsoftprobation.Presenter

import android.app.IntentService
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView

class JsonHelperIntentService : IntentService("BackgroundIntentService") {

    fun start(
        recyclerView: RecyclerView,
        context: Context
    ) {
        val intentService = Intent(context, JsonHelperIntentService::class.java)
        context.startService(intentService)
        recyclerView.adapter = NewsAdapter()
    }

    override fun onHandleIntent(intent: Intent?) {
        Datas.events = JsonHelper(this).getEvents()
    }
}