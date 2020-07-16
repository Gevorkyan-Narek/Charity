package com.cyclone.simbirsoftprobation.View

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cyclone.simbirsoftprobation.R
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)

        navigation.setOnNavigationItemSelectedListener { menuItem ->
            val transaction = supportFragmentManager.beginTransaction()
            when (menuItem.itemId) {
                R.id.profile -> {
                    transaction.replace(R.id.main_view_fragment, ProfileFragment())
                }
                R.id.help -> {
                    transaction.replace(R.id.main_view_fragment, HelpFragment())
                }
                R.id.search -> {
                    transaction.replace(R.id.main_view_fragment, SearchFragment())
                }
                else -> {
                    Toast.makeText(this, "Yet not added", Toast.LENGTH_SHORT).show()
                }
            }
            transaction.commit()
            true
        }
        navigation.selectedItemId = R.id.help

        floatingButton.setOnClickListener { navigation.selectedItemId = R.id.help }
    }
}