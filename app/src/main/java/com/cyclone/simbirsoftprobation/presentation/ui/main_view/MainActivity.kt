package com.cyclone.simbirsoftprobation.presentation.ui.main_view

import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.cyclone.simbirsoftprobation.R
import com.cyclone.simbirsoftprobation.domain.dagger.components.DaggerMainActivityComponent
//import com.cyclone.simbirsoftprobation.domain.dagger.components.DaggerMainActivityComponent
import com.cyclone.simbirsoftprobation.presentation.presenter.MainPresenter
import com.cyclone.simbirsoftprobation.presentation.ui.category_of_help.CategoryOfHelpFragment
import com.cyclone.simbirsoftprobation.presentation.ui.news.NewsFragment
import com.cyclone.simbirsoftprobation.presentation.ui.profile.ProfileFragment
import com.cyclone.simbirsoftprobation.presentation.ui.search.view.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(),
    MainView {

    @Inject
    lateinit var categoryOfHelpFragment: CategoryOfHelpFragment
    @Inject
    lateinit var newsFragment: NewsFragment
    @Inject
    lateinit var searchFragment: SearchFragment
    @Inject
    lateinit var profileFragment: ProfileFragment

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter
    private val rotation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMainActivityComponent.create().inject(this)

        navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.news -> {
                    mainPresenter.showFragment(MainPresenter.EVENTS)
                }
                R.id.search -> {
                    mainPresenter.showFragment(MainPresenter.SEARCH)
                }
                R.id.help -> {
                    mainPresenter.showFragment(MainPresenter.CATEGORIES)
                }
                R.id.profile -> {
                    mainPresenter.showFragment(MainPresenter.PROFILE)
                }
                else -> {
                    Toast.makeText(this, "Yet not added", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

        if (savedInstanceState == null) {
            navigation.selectedItemId = R.id.help
        }

        floatingButton.setOnClickListener { navigation.selectedItemId = R.id.help }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("Rotation", !rotation)
    }

    override fun showCategoryOfHelp() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_view_fragment,
                categoryOfHelpFragment
            )
            .commit()
    }

    override fun showNews() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_view_fragment,
                newsFragment
            )
            .commit()
    }

    override fun showSearch() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_view_fragment,
                searchFragment
            )
            .commit()
    }

    override fun showProfile() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_view_fragment,
                profileFragment
            )
            .commit()
    }
}