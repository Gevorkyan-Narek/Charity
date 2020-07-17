package com.cyclone.simbirsoftprobation.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclone.simbirsoftprobation.Model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.Model.Event
import com.cyclone.simbirsoftprobation.Presenter.Datas
import com.cyclone.simbirsoftprobation.Presenter.DiffUtils
import com.cyclone.simbirsoftprobation.Presenter.NewsAdapter
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.news_fragment.view.*
import java.io.Serializable

class NewsFragment : Fragment(R.layout.news_fragment) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)!!
        view.news_recycler.layoutManager = LinearLayoutManager(context)
        view.news_recycler.adapter = NewsAdapter(Datas.getInstance().events)
        view.filter.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_view_fragment, FilterFragment())?.addToBackStack("filter")
                ?.commit()
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        view?.news_recycler?.adapter?.notifyDataSetChanged()
    }
}