package com.cyclone.simbirsoftprobation.presentation.ui.category_of_help

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.domain.repository.category_of_help.CategoriesDataRepository
import com.cyclone.simbirsoftprobation.domain.repository.network.RetrofitDataRepository
import com.cyclone.simbirsoftprobation.presentation.presenter.CategoryOfHelpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.help_fragment.*
import javax.inject.Inject

class CategoryOfHelpFragment @Inject constructor() : MvpAppCompatFragment(),
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler_kind_of_help.layoutManager = GridLayoutManager(context, 2)
        val spaceItemDecoration = object : RecyclerView.ItemDecoration() {
            var spanCount = resources.getInteger(R.integer.columnCount)
            var spacing = resources.getInteger(R.integer.columnSpacing)

            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val position = parent.getChildAdapterPosition(view) // item position
                val column = position % spanCount // item column

                outRect.left =
                    column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right =
                    spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }

            }
        }
        recycler_kind_of_help.addItemDecoration(spaceItemDecoration)
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