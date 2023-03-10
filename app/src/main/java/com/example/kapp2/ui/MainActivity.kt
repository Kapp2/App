package com.example.kapp2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.kapp2.R
import com.example.kapp2.adapters.TabPageAdapter
import com.example.kapp2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpTabBar()
    }

    private fun setUpTabBar() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Login").setIcon(R.drawable.ic_home_black_24dp))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Botones").setIcon(R.drawable.ic_dashboard_black_24dp))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Favoritos").setIcon(R.drawable.star_favorite_24))

        val adapter = TabPageAdapter(this, binding.tabLayout.tabCount)
        binding.viewPager.adapter = adapter

        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

        binding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }
}