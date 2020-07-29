package com.cyclone.simbirsoftprobation.View

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclone.simbirsoftprobation.Presenter.JsonHelperExecutor
import com.cyclone.simbirsoftprobation.Presenter.JsonHelperIntentService
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.news_fragment.view.*

class NewsFragment : Fragment(R.layout.news_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.news_recycler.layoutManager = LinearLayoutManager(context)

        // Async
//        JsonHelperAsync(view.context, view.news_recycler).execute()

        // Executor
//        JsonHelperExecutor().submit(view.context, view.news_recycler)

        // IntentService
        JsonHelperIntentService().start(view.news_recycler, view.context)

        Toast.makeText(context, "toast", Toast.LENGTH_SHORT).show()
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