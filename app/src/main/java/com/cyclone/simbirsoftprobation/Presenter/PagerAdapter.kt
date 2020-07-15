package com.cyclone.simbirsoftprobation.Presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cyclone.simbirsoftprobation.View.SearchResultFragment



class PagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        const val ARG_OBJECT = "object"
    }
    override fun getItem(position: Int): Fragment {
        val fragment = SearchResultFragment()
        fragment.arguments = Bundle().apply { putInt(ARG_OBJECT, position + 1) }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 1) "По НКО" else "По мероприятиям"
    }

    override fun getCount(): Int = 2
}