package com.cyclone.simbirsoftprobation.json_helper

import android.content.Context
import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.model.Event
import com.cyclone.simbirsoftprobation.news.NewsAdapter
import com.cyclone.simbirsoftprobation.storage.Datas

class JsonHelperAsync(
    var context: Context,
    var recyclerView: RecyclerView,
    var progressBar: ProgressBar
) : AsyncTask<Void, Int, MutableList<Event>>() {

    override fun doInBackground(vararg params: Void): MutableList<Event>? {
//        Thread.sleep(5000)
        return JsonHelper(context).getEvents()
    }

    override fun onPostExecute(result: MutableList<Event>?) {
        progressBar.visibility = View.GONE
        Datas.events = result!!
        recyclerView.adapter = NewsAdapter()
    }
}