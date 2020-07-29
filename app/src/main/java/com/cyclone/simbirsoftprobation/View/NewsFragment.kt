package com.cyclone.simbirsoftprobation.View

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.Presenter.*
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.news_fragment.view.*

class NewsFragment : Fragment(R.layout.news_fragment) {

    lateinit var broadcastReceiver: MyBroadcastReceiver
    var isLoaded = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)!!

        if (savedInstanceState == null) {
            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            // Async
            JsonHelperAsync(view.context, view.news_recycler).execute()

            // Executor
//        JsonHelperExecutor().submit(view.context, view.news_recycler)

            // IntentService
//        JsonHelperIntentService().start(view.news_recycler.adapter as NewsAdapter, view.context)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.news_recycler.layoutManager = LinearLayoutManager(context)
        view.news_recycler.adapter = NewsAdapter()

        Toast.makeText(context, "toast", Toast.LENGTH_SHORT).show()
        view.filter.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_view_fragment, FilterFragment())?.addToBackStack("filter")
                ?.commit()
        }

        broadcastReceiver = MyBroadcastReceiver(view.news_recycler)
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

}