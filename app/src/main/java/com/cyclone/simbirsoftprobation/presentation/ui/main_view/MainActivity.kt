package com.cyclone.simbirsoftprobation.presentation.ui.main_view

import android.os.Bundle
import android.widget.Toast
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.presentation.presenter.MainPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.category_of_help.CategoryOfHelpFragment
import com.cyclone.simbirsoftprobation.presentation.ui.filter.FilterFragment
import com.cyclone.simbirsoftprobation.presentation.ui.news.NewsFragment
import com.cyclone.simbirsoftprobation.presentation.ui.profile.ProfileFragment
import com.cyclone.simbirsoftprobation.presentation.ui.search.view.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(R.layout.activity_main),
    MainView {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.news -> mainPresenter.switchToNews()
                R.id.search -> mainPresenter.switchToSearch()
                R.id.help -> mainPresenter.switchToCategories()
                R.id.profile -> mainPresenter.switchToProfile()
                else -> Toast.makeText(this, "Yet not added", Toast.LENGTH_SHORT).show()
            }
            true
        }
        floatingButton.setOnClickListener { navigation.selectedItemId = R.id.help }
    }

    override fun showCategoryOfHelp() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_view_fragment,
                CategoryOfHelpFragment()
            )
            .commit()
    }

    override fun showNews() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_view_fragment,
                NewsFragment()
            )
            .commit()
    }

    override fun showSearch() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_view_fragment,
                SearchFragment()
            )
            .commit()
    }

    override fun showProfile() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_view_fragment,
                ProfileFragment()
            )
            .commit()
    }

    override fun showFilter() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_view_fragment,
                FilterFragment()
            ).addToBackStack("filter")
            .commit()
    }
}