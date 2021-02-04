package com.cyclone.simbirsoftprobation.news

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.filter.FilterFragment
import com.cyclone.simbirsoftprobation.json_helper.JsonHelperAsync
import com.cyclone.simbirsoftprobation.json_helper.JsonHelperCallback
import com.cyclone.simbirsoftprobation.json_helper.JsonHelperIntentService
import com.cyclone.simbirsoftprobation.json_helper.MyBroadcastReceiver
import com.cyclone.simbirsoftprobation.model.Event
import kotlinx.android.synthetic.main.news_fragment.view.*

class NewsFragment : Fragment(R.layout.news_fragment), JsonHelperCallback<MutableList<Event>> {

    lateinit var broadcastReceiver: MyBroadcastReceiver
    var isLoaded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.news_recycler.layoutManager = LinearLayoutManager(context)

        if (savedInstanceState == null) {
            // Async
            JsonHelperAsync(view.context, this).execute()

            // Executor
//            JsonHelperExecutor().submit(view.context, this)

            // IntentService
//            JsonHelperIntentService().start(view.context)
        }

        view.filter.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.main_view_fragment,
                    FilterFragment()
                )?.addToBackStack("filter")
                ?.commit()
        }

        broadcastReceiver = MyBroadcastReceiver(view.news_recycler, view.progressBarNews)
        val intentFilter = IntentFilter(JsonHelperIntentService.ACTION)
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT)
        context?.registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onResume() {
        super.onResume()
        view?.news_recycler?.adapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.unregisterReceiver(broadcastReceiver)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isLoaded", true)
    }

    override fun onSuccess(result: MutableList<Event>) {
        view!!.progressBarNews.visibility = View.GONE
        view!!.news_recycler.adapter = NewsAdapter()
    }

    override fun onFailure(e: Exception) {
        e.printStackTrace()
    }
}