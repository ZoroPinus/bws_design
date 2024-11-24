package com.example.bws_design.Dashboard.ui.history

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bws_design.Dashboard.ui.history.tabs.PartTimeJobFragment
import com.example.bws_design.Dashboard.ui.history.tabs.SummaryFragment
import com.example.bws_design.Dashboard.ui.history.tabs.WasteGetPointsFragment

class HistoryPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3 // 3 tabs

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SummaryFragment()
            1 -> WasteGetPointsFragment()
            2 -> PartTimeJobFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}