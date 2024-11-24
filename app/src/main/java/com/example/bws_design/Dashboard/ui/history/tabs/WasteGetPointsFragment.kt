package com.example.bws_design.Dashboard.ui.history.tabs

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bws_design.R
import com.example.bws_design.databinding.FragmentWasteGetPointsBinding

class WasteGetPointsFragment : Fragment() {
    private var _binding: FragmentWasteGetPointsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWasteGetPointsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wastes = listOf(
            WasteDataModel("Plastic Bottles", "2023-12-20", 10),
            WasteDataModel("Paper", "2023-12-19", 5),
        )

        val adapter = WasteAdapter(wastes)
        binding.wastesRecyclerView.adapter = adapter
        binding.wastesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.addWasteButton.setOnClickListener {
            val intent = Intent(requireContext(), AddWasteActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}