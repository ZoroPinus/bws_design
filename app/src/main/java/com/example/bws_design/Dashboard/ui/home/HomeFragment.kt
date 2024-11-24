package com.example.bws_design.Dashboard.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bws_design.R
import com.example.bws_design.Utils.CardItemDecoration
import com.example.bws_design.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Replace with real data from firestore
        val userName = "Lee"
        val welcomeText = getString(R.string.welcome_message, userName)
        binding.welcomeText.text = welcomeText

        // Replace with user uploaded image
        Glide.with(this)
            .load(R.drawable.profile) // Replace with the actual image URL or resource
            .placeholder(R.drawable.ic_profile)
            .error(R.drawable.ic_profile)
            .circleCrop()
            .into(binding.userProfileImage)

        //  Cards Data
        val servicesCardList = mutableListOf(
            CarouselData(R.drawable.sampleimage, "Property Information"),
            CarouselData(R.drawable.sampleimage, "Maritim  Infrastructure"),
        )
        val communityCardList = mutableListOf(
            CarouselData(R.drawable.sampleimage, "Environmental interests"),
            CarouselData(R.drawable.sampleimage, "Green House"),
        )
        // Card layout helper
        setupCardCarousel(binding.servicesCarousel, servicesCardList)
        setupCardCarousel(binding.communityCarousel, communityCardList)

        // Card Gestures
        val servicesItemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val recyclerView = viewHolder.itemView.parent as RecyclerView // Get RecyclerView from itemView

                when (recyclerView) {
                    binding.servicesCarousel -> {
                        servicesCardList.removeAt(position)
                        (binding.servicesCarousel.adapter as? CarouselAdapter)?.notifyItemRemoved(position)
                    }
                    binding.communityCarousel -> {
                        communityCardList.removeAt(position)
                        (binding.communityCarousel.adapter as? CarouselAdapter)?.notifyItemRemoved(position)
                    }
                }
            }
        }
        val communityItemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val recyclerView = viewHolder.itemView.parent as RecyclerView // Get RecyclerView from itemView

                when (recyclerView) {
                    binding.servicesCarousel -> {
                        servicesCardList.removeAt(position)
                        (binding.servicesCarousel.adapter as? CarouselAdapter)?.notifyItemRemoved(position)
                    }
                    binding.communityCarousel -> {
                        communityCardList.removeAt(position)
                        (binding.communityCarousel.adapter as? CarouselAdapter)?.notifyItemRemoved(position)
                    }
                }
            }
        }

        val servicesItemTouchHelper = ItemTouchHelper(servicesItemTouchHelperCallback)
        val communityItemTouchHelper = ItemTouchHelper(communityItemTouchHelperCallback)

        val servicesSnapHelper = PagerSnapHelper()
        val communitySnapHelper = PagerSnapHelper()

        servicesSnapHelper.attachToRecyclerView(binding.servicesCarousel)
        servicesItemTouchHelper.attachToRecyclerView(binding.servicesCarousel)

        communitySnapHelper.attachToRecyclerView(binding.communityCarousel)
        communityItemTouchHelper.attachToRecyclerView(binding.communityCarousel)

        return root
    }

    private fun setupCardCarousel(recyclerView: RecyclerView, cardList: List<CarouselData>) {
        val adapter = CarouselAdapter(cardList) // Or ListAdapter
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
            addItemDecoration(CardItemDecoration(requireContext()))
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}