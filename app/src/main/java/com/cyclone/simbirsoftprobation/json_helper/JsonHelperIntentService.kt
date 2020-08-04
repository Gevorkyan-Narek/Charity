package com.cyclone.simbirsoftprobation.json_helper

import android.app.IntentService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.news.NewsAdapter
import com.cyclone.simbirsoftprobation.storage.Datas
import com.cyclone.simbirsoftprobation.utilities.getFilteredEvents

class JsonHelperIntentService : IntentService("BackgroundIntentService") {

    companion object {
        const val ACTION = "LOAD_FILES"
        const val EXTRA_KEY_OUT = "EXTRA_OUT"
        const val finished = "finished"
    }

    lateinit var adapter: NewsAdapter

    fun start(
        context: Context
    ) {
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

class MyBroadcastReceiver(
    private val recyclerView: RecyclerView,
    private val progressBar: ProgressBar
) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        progressBar.visibility = View.GONE
        recyclerView.adapter = NewsAdapter()
    }
}