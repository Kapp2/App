package com.example.kapp2.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kapp2.ui.botones.BotonesFragment
import com.example.kapp2.ui.favoritos.FavoritosFragment
import com.example.kapp2.ui.home.HomeFragment

class TabPageAdapter(activity: FragmentActivity, private val tabCount: Int) : FragmentStateAdapter(activity)
{
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> BotonesFragment()
            2 -> FavoritosFragment()
            else -> HomeFragment()
        }
    }
}