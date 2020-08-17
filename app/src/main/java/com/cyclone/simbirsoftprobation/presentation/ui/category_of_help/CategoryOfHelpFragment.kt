package com.cyclone.simbirsoftprobation.presentation.ui.category_of_help

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.domain.repository.category_of_help.CategoriesDataRepository
import com.cyclone.simbirsoftprobation.domain.repository.network.RetrofitDataRepository
import com.cyclone.simbirsoftprobation.presentation.presenter.CategoryOfHelpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.help_fragment.*
import kotlinx.android.synthetic.main.help_fragment.view.*
import javax.inject.Inject

class CategoryOfHelpFragment @Inject constructor(): MvpAppCompatFragment(),
    CategoryOfHelpView {

    @InjectPresenter
    lateinit var categoryOfHelpPresenter: CategoryOfHelpPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.help_fragment, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.recycler_kind_of_help.layoutManager = GridLayoutManager(context, 2)
    }

    override fun showCategories() {
        CategoriesDataRepository
            .getInstance()
            .getCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { categories ->
                recycler_kind_of_help.adapter =
                    CategoryOfHelpAdapter(categories.toMutableList())
                progressBarCategoryHelp.visibility = View.GONE
            }
            .subscribe()
    }

    override fun updateCategories() {
        RetrofitDataRepository.getInstance().fillCategoriesDB()
    }
}