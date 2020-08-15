package com.cyclone.simbirsoftprobation.search.adapter

import android.os.Bundle
import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cyclone.simbirsoftprobation.search.view.SearchResultFragment


class PagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val registeredFragments = SparseArray<SearchResultFragment>()

    companion object {
        const val ARG_OBJECT = "object"
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as SearchResultFragment
        registeredFragments.put(position, fragment)
        return fragment
    }

    override fun getItem(position: Int): Fragment {
        val fragment = SearchResultFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 1) "По НКО" else "По мероприятиям"
    }

    override fun getCount(): Int = 2

    fun getRegisteredFragments(position: Int): SearchResultFragment = registeredFragments[position]

    fun updateResults(position: Int, isNotBlank: Boolean) {
        registeredFragments[position].update(isNotBlank)
    }
}