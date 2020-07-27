package com.cyclone.simbirsoftprobation.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclone.simbirsoftprobation.Presenter.JsonHelperAsync
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.news_fragment.view.*
import java.util.concurrent.ExecutorService

class NewsFragment : Fragment(R.layout.news_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.news_recycler.layoutManager = LinearLayoutManager(context)
        Log.d("AsyncTask", "PreStart")
        // Async
        JsonHelperAsync(activity?.assets!!, view.news_recycler).execute()
        // Executor
//        val executor =
//        view.news_recycler.adapter = NewsAdapter(Datas.events)
        view.filter.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_view_fragment, FilterFragment())?.addToBackStack("filter")
                ?.commit()
        }
    }

    override fun onResume() {
        super.onResume()
        view?.news_recycler?.adapter?.notifyDataSetChanged()
    }
}