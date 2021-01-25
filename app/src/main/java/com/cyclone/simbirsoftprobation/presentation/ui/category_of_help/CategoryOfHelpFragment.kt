package com.cyclone.simbirsoftprobation.presentation.ui.category_of_help

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.databinding.HelpFragmentBinding
import com.cyclone.simbirsoftprobation.domain.model.CategoryOfHelp
import com.cyclone.simbirsoftprobation.presentation.presenter.CategoryOfHelpPresenter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class CategoryOfHelpFragment : MvpAppCompatFragment(), CategoryOfHelpView {

    @InjectPresenter
    lateinit var categoryOfHelpPresenter: CategoryOfHelpPresenter
    private lateinit var binding: HelpFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HelpFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

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
        binding.recyclerKindOfHelp.addItemDecoration(spaceItemDecoration)
    }

    override fun showCategories(categories: List<CategoryOfHelp>) {
        binding.recyclerKindOfHelp.adapter =
            CategoryOfHelpAdapter(categories)
        binding.progressBarCategoryHelp.visibility = View.GONE
    }
}