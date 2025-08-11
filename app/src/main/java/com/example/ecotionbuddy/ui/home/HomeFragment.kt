package com.example.ecotionbuddy.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ecotionbuddy.ChatbotActivity
import com.example.ecotionbuddy.ScanActivity
import com.example.ecotionbuddy.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupObservers()
        setupClickListeners()
        
        return root
    }
    
    private fun setupObservers() {
        homeViewModel.user.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.tvGreeting.text = "Hai, ${it.name.split(" ").first()}!"
                binding.tvPoints.text = "${it.points:,}".replace(",", ".")
            }
        }
        
        homeViewModel.featuredMission.observe(viewLifecycleOwner) { mission ->
            mission?.let {
                binding.tvMissionTitle.text = it.title
                binding.tvMissionDesc.text = it.description
                binding.tagPoints.text = "${it.pointsReward} Poin"
                binding.tagPlastik.text = it.category.displayName
                
                val daysLeft = ((it.deadline - System.currentTimeMillis()) / (24 * 60 * 60 * 1000)).toInt()
                binding.tvDaysLeft.text = "$daysLeft hari lagi"
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.btnTanyaAI.setOnClickListener {
            startActivity(Intent(requireContext(), ChatbotActivity::class.java))
        }
        
        binding.btnDiyRecommendation.setOnClickListener {
            startActivity(Intent(requireContext(), ScanActivity::class.java))
        }
        
        binding.btnStartMission.setOnClickListener {
            // Navigate to mission details or start mission flow
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}