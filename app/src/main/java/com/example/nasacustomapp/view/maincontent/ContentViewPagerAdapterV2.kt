package com.example.nasacustomapp.view.maincontent

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ContentViewPagerAdapterV2(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val fragments =
        arrayOf(PhotoOfTheDayFragment.newInstance(), ConstraintFragment.newInstance(), CoordinatorFragment.newInstance())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}