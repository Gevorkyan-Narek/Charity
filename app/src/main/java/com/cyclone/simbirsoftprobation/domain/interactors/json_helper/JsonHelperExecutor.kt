package com.cyclone.simbirsoftprobation.domain.interactors.json_helper

import android.content.Context
import android.os.Handler
import com.cyclone.simbirsoftprobation.data.json_helper.JsonHelper
import com.cyclone.simbirsoftprobation.db.EventDataBase
import com.cyclone.simbirsoftprobation.domain.model.Event
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class JsonHelperExecutor {

    fun submit(
        context: Context,
        callback: JsonHelperCallback<MutableList<Event>>?
    ) {

        Handler {
            val executor = Executors.newSingleThreadExecutor()

            lateinit var events: MutableList<Event>

            executor.submit {
//                Thread.sleep(5000)
                events = JsonHelper(
                    context
                )
                    .getEvents()
                EventDataBase.getDataBase().eventDAO().insertEvents(events)
            }

            var exception: Exception? = null

            try {
                executor.shutdown()
                executor.awaitTermination(10, TimeUnit.SECONDS)
            } catch (e: InterruptedException) {
                exception = e
            } finally {
                if (!executor.isShutdown) executor.shutdownNow()

                if (callback != null && exception != null) {
                    callback.onFailure(exception)
                }
            }

            true

        }.sendEmptyMessage(0)
    }
}