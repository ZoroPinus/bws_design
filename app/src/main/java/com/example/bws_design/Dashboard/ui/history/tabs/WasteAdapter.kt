package com.example.bws_design.Dashboard.ui.history.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.semantics.text
import androidx.recyclerview.widget.RecyclerView
import com.example.bws_design.databinding.WasteItemLayoutBinding

class WasteAdapter(private val wastes: List<WasteDataModel>) : RecyclerView.Adapter<WasteAdapter.WasteViewHolder>() {

    class WasteViewHolder(private val binding: WasteItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(waste: WasteDataModel) {
            binding.wasteTitle.text = waste.title
            binding.wasteDate.text = waste.date
            binding.wastePoints.text = waste.points.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WasteViewHolder {
        val binding = WasteItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WasteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WasteViewHolder, position: Int) {
        holder.bind(wastes[position])
    }

    override fun getItemCount(): Int = wastes.size
}