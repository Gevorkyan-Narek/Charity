package com.cyclone.simbirsoftprobation.json_helper

import android.content.Context
import android.os.Handler
import com.cyclone.simbirsoftprobation.model.Event
import com.cyclone.simbirsoftprobation.storage.Datas
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class JsonHelperExecutor {

    fun submit(
        context: Context,
        callback: JsonHelperCallback<MutableList<Event>>?
    ) {

        Handler {
            val executor = Executors.newSingleThreadExecutor()

            executor.submit {
                Thread.sleep(5000)
                Datas.events = JsonHelper(context).getEvents()
            }

            var exception: Exception? = null

            try {
                executor.shutdown()
                executor.awaitTermination(10, TimeUnit.SECONDS)
            } catch (e: InterruptedException) {
                exception = e
            } finally {
                if (!executor.isShutdown) executor.shutdownNow()
                if (callback != null) {
                    if (exception == null) {
                        callback.onSuccess(Datas.events)
                    } else callback.onFailure(exception)
                }
            }

            true

        }.sendEmptyMessage(0)
    }
//    fun submit(
//        context: Context,
//        newsRecycler: RecyclerView,
//        progressBar: ProgressBar
//    ) {
//
//        Handler {
//            val executor = Executors.newSingleThreadExecutor()
//
//            executor.submit {
//                Thread.sleep(5000)
//                Datas.events = JsonHelper(context).getEvents()
//            }
//
//            try {
//                executor.shutdown()
//                executor.awaitTermination(10, TimeUnit.SECONDS)
//            } catch (e: InterruptedException) {
//                e.printStackTrace()
//            } finally {
//                if (!executor.isShutdown) executor.shutdownNow()
//                progressBar.visibility = View.GONE
//                newsRecycler.adapter =
//                    NewsAdapter()
//            }
//
//            true
//
//        }.sendEmptyMessage(0)
//    }
}