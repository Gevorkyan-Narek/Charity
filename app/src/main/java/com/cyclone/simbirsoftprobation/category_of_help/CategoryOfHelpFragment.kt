package com.cyclone.simbirsoftprobation.category_of_help

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.db.EventDataBase
import com.cyclone.simbirsoftprobation.model.Filter
import com.cyclone.simbirsoftprobation.storage.Datas
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.help_fragment.view.*

class CategoryOfHelpFragment : Fragment(R.layout.help_fragment) {

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.recycler_kind_of_help.layoutManager = GridLayoutManager(context, 2)

        EventDataBase.getDataBase(view.context)
            .categoriesDAO()
            .getCategories()
            .doOnNext {
                Datas.filter = it.map { Filter(it.id, it.name) }.toMutableList()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { categories ->
                view.recycler_kind_of_help.adapter =
                    CategoryOfHelpAdapter(categories.toMutableList())
                view.progressBarCategoryHelp.visibility = View.GONE            }
            .subscribe()
    }
}