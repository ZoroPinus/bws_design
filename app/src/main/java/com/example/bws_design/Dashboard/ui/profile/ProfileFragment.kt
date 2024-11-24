package com.example.bws_design.Dashboard.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.semantics.text
import com.bumptech.glide.Glide
import com.example.bws_design.Auth.LoginActivity
import com.example.bws_design.R
import com.example.bws_design.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val toolbarTitle = view?.findViewById<TextView>(R.id.app_title)

        toolbarTitle?.text = "Profile"
        Glide.with(this)
            .load(R.drawable.profile) // Replace with the actual image URL or resource
            .placeholder(R.drawable.ic_profile)
            .error(R.drawable.ic_profile)
            .circleCrop()
            .into(binding.userProfileImage)

        binding.usernameValue.text = "John Doe"
        binding.emailValue.text = "john.doe@example.com"
        binding.maritalValue.text = "Single"
        binding.username.text = "John Doe"
        binding.email.text = "john.doe@example.com"

        binding.logoutButton.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}