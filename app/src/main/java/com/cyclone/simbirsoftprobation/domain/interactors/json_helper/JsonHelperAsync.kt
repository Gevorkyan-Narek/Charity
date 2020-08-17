package com.cyclone.simbirsoftprobation.domain.interactors.json_helper

import android.content.Context
import android.os.AsyncTask
import com.cyclone.simbirsoftprobation.data.json_helper.JsonHelper
import com.cyclone.simbirsoftprobation.db.EventDataBase
import com.cyclone.simbirsoftprobation.domain.model.Event

class JsonHelperAsync(var context: Context, var callback: JsonHelperCallback<MutableList<Event>>?) :
    AsyncTask<Void, Int, Boolean>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: Void): Boolean {
//        Thread.sleep(5000)
        lateinit var events: MutableList<Event>
        try {
            events = JsonHelper(
                context
            ).getEvents()
            EventDataBase.getDataBase().eventDAO().insertEvents(events)
        } catch (e: Exception) {
            exception = e
        }
        return true
    }

    override fun onPostExecute(result: Boolean?) {
        if (callback != null && exception != null) {
            callback!!.onFailure(exception!!)
        }
    }
}