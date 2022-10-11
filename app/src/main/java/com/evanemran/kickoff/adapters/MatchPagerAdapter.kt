package com.evanemran.kickoff.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MatchPagerAdapter : FragmentPagerAdapter {

    var fragmentArrayList: ArrayList<Fragment> = arrayListOf()
    var stringArrayList: ArrayList<String> = arrayListOf()

    fun addFragment(fragment: Fragment, title: String) {
        fragmentArrayList.add(fragment)
        stringArrayList.add(title)
    }

    constructor(fm: FragmentManager) : super(fm)
    constructor(fm: FragmentManager, behavior: Int) : super(fm, behavior)

    override fun getCount(): Int {
        return fragmentArrayList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentArrayList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return stringArrayList[position]
    }
}