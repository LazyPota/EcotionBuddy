package com.example.ecotionbuddy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.ecotionbuddy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
        setupClickListeners()
        loadUserData()
    }
    
    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Already on home
                    true
                }
                R.id.navigation_ask_ai -> {
                    startActivity(Intent(this, ChatbotActivity::class.java))
                    true
                }
                R.id.navigation_history -> {
                    startActivity(Intent(this, HistoryActivity::class.java))
                    true
                }
                R.id.navigation_account -> {
                    startActivity(Intent(this, AccountActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.btnTanyaAI.setOnClickListener {
            startActivity(Intent(this, ChatbotActivity::class.java))
        }
        
        binding.btnDiyRecommendation.setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java))
        }
        
        binding.btnStartMission.setOnClickListener {
            // Navigate to mission details
        }
        
        binding.cardPoints.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
    
    private fun loadUserData() {
        // For demo purposes, using static data
        binding.apply {
            tvGreeting.text = "Hai, Rafli!"
            tvPoints.text = "150.000"
            tvMissionTitle.text = "Kumpulkan Sampah Plastik"
            tvMissionDesc.text = "Ayo kumpulkan sampah plastik yang ada di sekitar rumahmu untuk mendapatkan poin tambahan!"
            tvDaysLeft.text = "30 hari lagi"
        }
    }
}