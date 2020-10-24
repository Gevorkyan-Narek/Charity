package com.cyclone.simbirsoftprobation.presentation.ui.category_of_help

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.domain.model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.presentation.presenter.CategoryOfHelpPresenter
import kotlinx.android.synthetic.main.help_fragment.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class CategoryOfHelpFragment : MvpAppCompatFragment(R.layout.help_fragment), CategoryOfHelpView {

    @InjectPresenter
    lateinit var categoryOfHelpPresenter: CategoryOfHelpPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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

    override fun showCategories(categories: List<CategoryOfHelp>) {
        recycler_kind_of_help.adapter =
            CategoryOfHelpAdapter(categories.toMutableList())
        progressBarCategoryHelp.visibility = View.GONE
    }
}