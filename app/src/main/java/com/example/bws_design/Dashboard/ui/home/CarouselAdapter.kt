package com.example.bws_design.Dashboard.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bws_design.databinding.CarouselItemLayoutBinding

class CarouselAdapter(private val cardList: List<CarouselData>):
    RecyclerView.Adapter<CarouselAdapter.CardViewHolder>() {
    class CardViewHolder(private val binding: CarouselItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val cardImage: ImageView = binding.cardImage
        val cardTitle: TextView = binding.cardTitle
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = CarouselItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentItem = cardList[position]
        // Set data to your card views here
        holder.cardImage.setImageResource(currentItem.imageResource)
        holder.cardTitle.text = currentItem.title
    }

    override fun getItemCount(): Int {
        return cardList.size
    }
}