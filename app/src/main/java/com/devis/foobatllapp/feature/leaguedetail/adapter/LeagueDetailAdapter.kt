package com.devis.foobatllapp.feature.leaguedetail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Created by Devis on 27/09/20
 */

class LeagueDetailAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val tabNames: ArrayList<String> = arrayListOf()
    private val fragments: ArrayList<Fragment> = arrayListOf()

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabNames[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    fun add(fragment: Fragment, title: String) {
        tabNames.add(title)
        fragments.add(fragment)
    }

}