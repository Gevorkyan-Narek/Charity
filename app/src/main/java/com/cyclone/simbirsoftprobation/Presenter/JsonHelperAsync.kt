package com.cyclone.simbirsoftprobation.Presenter

import android.content.Context
import android.os.AsyncTask
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.Model.Event

class JsonHelperAsync(
    var context: Context,
    var recyclerView: RecyclerView
) : AsyncTask<Void, Int, MutableList<Event>>() {

    override fun doInBackground(vararg params: Void): MutableList<Event>? {
//        Thread.sleep(5000)
        return JsonHelper(context).getEvents()
    }

    override fun onPostExecute(result: MutableList<Event>?) {
        Datas.events = result!!
        recyclerView.adapter = NewsAdapter()
    }
}