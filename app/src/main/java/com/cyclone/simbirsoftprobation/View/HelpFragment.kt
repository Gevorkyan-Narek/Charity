package com.cyclone.simbirsoftprobation.View

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.cyclone.simbirsoftprobation.Presenter.Datas
import com.cyclone.simbirsoftprobation.Presenter.HelpsAdapter
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.help_fragment.view.*
import kotlinx.android.synthetic.main.news_fragment.view.*

class HelpFragment : Fragment(R.layout.help_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.recycler_kind_of_help.layoutManager = GridLayoutManager(context, 2)
        Handler {
            view.progressBarCategoryHelp.visibility = View.GONE
            view.recycler_kind_of_help.adapter = HelpsAdapter(Datas(resources).categoriesOfHelp)
            true
        }.sendEmptyMessageDelayed(0, 1000)
    }
}