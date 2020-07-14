package com.cyclone.simbirsoftprobation.View

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclone.simbirsoftprobation.Model.Help
import com.cyclone.simbirsoftprobation.Presenter.HelpsAdapter
import com.cyclone.simbirsoftprobation.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.help_fragment.*
import kotlinx.android.synthetic.main.help_fragment.view.*

class HelpFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.help_fragment, container, false)

        val helps = mutableListOf(
            Help("Дети", BitmapFactory.decodeResource(resources, R.drawable.children)),
            Help("Взрослые", BitmapFactory.decodeResource(resources, R.drawable.man)),
            Help("Пожилые", BitmapFactory.decodeResource(resources, R.drawable.grand)),
            Help("Животные", BitmapFactory.decodeResource(resources, R.drawable.animals)),
            Help("Мероприятия", BitmapFactory.decodeResource(resources, R.drawable.events))
        )

        val recyclerView = view.recycler_kind_of_help
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = HelpsAdapter(helps)

        return view
    }
}