package com.tafh.githubuserapp.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tafh.githubuserapp.view.fragment.ListFollowerFragment
import com.tafh.githubuserapp.view.fragment.ListFollowingFragment
import com.tafh.githubuserapp.view.fragment.ListRepositoryFragment

class SectionsPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = ListRepositoryFragment()
            1 -> fragment = ListFollowerFragment()
            2 -> fragment = ListFollowingFragment()
        }
        return fragment as Fragment
    }
}