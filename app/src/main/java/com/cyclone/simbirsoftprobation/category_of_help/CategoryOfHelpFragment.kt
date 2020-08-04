package com.cyclone.simbirsoftprobation.category_of_help

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.model.Filter
import com.cyclone.simbirsoftprobation.network.CategoriesFirebaseService
import com.cyclone.simbirsoftprobation.network.RetrofitInstance
import com.cyclone.simbirsoftprobation.storage.Datas
import kotlinx.android.synthetic.main.help_fragment.view.*
import rx.android.schedulers.AndroidSchedulers

class CategoryOfHelpFragment : Fragment(R.layout.help_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.recycler_kind_of_help.layoutManager = GridLayoutManager(context, 2)

        RetrofitInstance.instance
            .create(CategoriesFirebaseService::class.java)
            .getCategories()
            .onErrorReturn {
                Datas.categoriesOfHelp
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { categories ->
                view.progressBarCategoryHelp.visibility = View.GONE
                view.recycler_kind_of_help.adapter =
                    CategoryOfHelpAdapter(categories!!.toMutableList())
                Datas.filter = categories.map { Filter(it.id, it.name) }.toMutableList()
            }
            .subscribe()
    }
}