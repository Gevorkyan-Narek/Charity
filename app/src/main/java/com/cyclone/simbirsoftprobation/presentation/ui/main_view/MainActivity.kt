package com.cyclone.simbirsoftprobation.presentation.ui.main_view

import android.os.Bundle
import android.view.View
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.databinding.ActivityMainBinding
import com.cyclone.simbirsoftprobation.presentation.presenter.MainPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.auth.AuthorizationFragment
import com.cyclone.simbirsoftprobation.presentation.ui.category_of_help.CategoryOfHelpFragment
import com.cyclone.simbirsoftprobation.presentation.ui.filter.FilterFragment
import com.cyclone.simbirsoftprobation.presentation.ui.history.HistoryFragment
import com.cyclone.simbirsoftprobation.presentation.ui.news.NewsFragment
import com.cyclone.simbirsoftprobation.presentation.ui.profile.ProfileFragment
import com.cyclone.simbirsoftprobation.presentation.ui.search.view.SearchFragment
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(),
    MainView {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.news -> mainPresenter.switchToNews()
                R.id.search -> mainPresenter.switchToSearch()
                R.id.help -> mainPresenter.switchToCategories()
                R.id.profile -> mainPresenter.switchToProfile()
                R.id.history -> mainPresenter.switchToHistory()
            }
            true
        }
        binding.floatingButton.setOnClickListener { binding.navigation.selectedItemId = R.id.help }
    }

    override fun showCategoryOfHelp() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainFragment,
                CategoryOfHelpFragment()
            )
            .commit()
    }

    override fun showNews() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainFragment,
                NewsFragment()
            )
            .commit()
    }

    override fun showSearch() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainFragment,
                SearchFragment()
            )
            .commit()
    }

    override fun showProfile() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainFragment,
                ProfileFragment()
            )
            .commit()
    }

    override fun showFilter() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainFragment,
                FilterFragment()
            ).addToBackStack("filter")
            .commit()
    }

    override fun showAuth() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainFragment,
                AuthorizationFragment()
            ).addToBackStack("auth")
            .commit()
    }

    override fun visibleBottomBar() {
        binding.buttonBackground.visibility = View.VISIBLE
        binding.bottomAppBar.visibility = View.VISIBLE
        binding.floatingButton.visibility = View.VISIBLE
    }

    override fun invisibleBottomBar() {
        binding.buttonBackground.visibility = View.GONE
        binding.bottomAppBar.visibility = View.GONE
        binding.floatingButton.visibility = View.GONE
    }

    override fun switchHistory() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainFragment,
                HistoryFragment()
            ).addToBackStack("history")
            .commit()
    }
}