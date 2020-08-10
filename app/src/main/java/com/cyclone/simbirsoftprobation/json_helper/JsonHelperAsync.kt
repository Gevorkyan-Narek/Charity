package com.cyclone.simbirsoftprobation.json_helper

import android.content.Context
import android.os.AsyncTask
import com.cyclone.simbirsoftprobation.model.Event
import com.cyclone.simbirsoftprobation.storage.Datas

class JsonHelperAsync(var context: Context, var callback: JsonHelperCallback<MutableList<Event>>?) :
    AsyncTask<Void, Int, MutableList<Event>>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: Void): MutableList<Event>? {
//        Thread.sleep(5000)
        lateinit var events: MutableList<Event>
        try {
            events = JsonHelper(context).getEvents()
        } catch (e: Exception) {
            exception = e
        }
        return events
    }

    override fun onPostExecute(result: MutableList<Event>?) {
        Datas.events = result!!
        if (callback != null) {
            if (exception == null) {
                callback!!.onSuccess(result)
            } else callback!!.onFailure(exception!!)
        }
    }
}