package com.example.campusform

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class PagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private var lists = mutableListOf<Fragment>()
    private var titles = mutableListOf<String>()

    fun add(view: Fragment, title: String) {
        lists.add(view)
        titles.add(title)
    }

    override fun getItem(position: Int): Fragment = lists[position]

    override fun getCount(): Int = lists.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]

}