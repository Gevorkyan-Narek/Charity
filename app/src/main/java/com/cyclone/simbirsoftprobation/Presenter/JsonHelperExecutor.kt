package com.cyclone.simbirsoftprobation.Presenter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class JsonHelperExecutor {

    fun submit(
        context: Context,
        newsRecycler: RecyclerView
    ) {
        val executor = Executors.newSingleThreadExecutor()
        executor.submit {
//            Thread.sleep(5000)
            Datas.events = JsonHelper(context).getEvents()
            newsRecycler.adapter = NewsAdapter()
        }.get(10, TimeUnit.SECONDS)

        executor.shutdown()
    }
}