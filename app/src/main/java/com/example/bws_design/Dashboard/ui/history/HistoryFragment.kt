package com.example.bws_design.Dashboard.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.semantics.text
import androidx.lifecycle.ViewModelProvider
import com.example.bws_design.R
import com.example.bws_design.databinding.FragmentHistoryBinding
import com.example.bws_design.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator


class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root

        // Access AppBar elements
        val toolbar = view.findViewById<Toolbar>(R.id.appbar)
        val toolbarTitle = view.findViewById<TextView>(R.id.app_title)

        toolbarTitle.text = "History"
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher
        }

        val adapter = HistoryPagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Summary"
                1 -> "Wast & GetPoint"
                2 -> "Part-Time Job"
                else -> null
            }
        }.attach()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}