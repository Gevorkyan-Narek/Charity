package com.cyclone.simbirsoftprobation.View

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cyclone.simbirsoftprobation.Presenter.Datas
import com.cyclone.simbirsoftprobation.R
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    val rotation = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidThreeTen.init(this)
        Datas.newInstance(resources)

        navigation.setOnNavigationItemSelectedListener { menuItem ->
            val transaction = supportFragmentManager.beginTransaction()
            when (menuItem.itemId) {
                R.id.news -> {
                    transaction.replace(R.id.main_view_fragment, NewsFragment())
                }
                R.id.search -> {
                    transaction.replace(R.id.main_view_fragment, SearchFragment())
                }
                R.id.help -> {
                    transaction.replace(R.id.main_view_fragment, HelpFragment())
                }
                R.id.profile -> {
                    transaction.replace(R.id.main_view_fragment, ProfileFragment())
                }
                else -> {
                    Toast.makeText(this, "Yet not added", Toast.LENGTH_SHORT).show()
                }
            }
            transaction.commit()
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
}