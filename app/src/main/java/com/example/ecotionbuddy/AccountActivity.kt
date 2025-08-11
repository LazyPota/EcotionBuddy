package com.example.ecotionbuddy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ecotionbuddy.databinding.ActivityAkunBinding

class AccountActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityAkunBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
        loadUserData()
    }
    
    private fun setupUI() {
        binding.backButton.setOnClickListener {
            finish()
        }
        
        // Setup menu item click listeners
        setupMenuClickListeners()
    }
    
    private fun setupMenuClickListeners() {
        // For now, these are placeholder implementations
        // In a real app, these would navigate to respective activities
        
        binding.root.findViewById<android.widget.TextView>(
            binding.root.context.resources.getIdentifier("account_menu_history", "id", packageName)
        )?.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
    
    private fun loadUserData() {
        // Load user data from repository
        // For demo purposes, using static data
        binding.apply {
            userName.text = "Muhammad Rafli"
            userEmail.text = "rafli@email.com"
            userPoints.text = "Total Poin: 150.000"
        }
    }
}