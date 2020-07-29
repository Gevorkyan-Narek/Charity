package com.cyclone.simbirsoftprobation.Presenter

import android.app.IntentService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView

class JsonHelperIntentService : IntentService("BackgroundIntentService") {

    companion object {
        val ACTION = "LOAD_FILES"
        val EXTRA_KEY_OUT = "EXTRA_OUT"
        val finished = "finished"
    }

    lateinit var adapter: NewsAdapter

    fun start(
        adapter: NewsAdapter,
        context: Context
    ) {
        this.adapter = adapter
        val intentService = Intent(context, JsonHelperIntentService::class.java)
        context.startService(intentService)
    }

    override fun onHandleIntent(intent: Intent?) {
//        Thread.sleep(5000)
        Datas.events = JsonHelper(this).getEvents()
        val response = Intent()
        response.action = ACTION
        response.addCategory(Intent.CATEGORY_DEFAULT)
        response.putExtra(EXTRA_KEY_OUT, finished)
        sendBroadcast(response)
    }
}

class MyBroadcastReceiver(val recyclerView: RecyclerView) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        recyclerView.adapter = NewsAdapter()
    }
}