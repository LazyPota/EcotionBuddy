package com.example.ecotionbuddy

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.ecotionbuddy.databinding.ActivityHistoryBinding // Ganti dengan package Anda

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // BARIS WAJIB UNTUK MENGAKTIFKAN TOOLBAR DARI XML
        setSupportActionBar(binding.toolbar)

        // Opsional: Menambahkan tombol kembali
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // ... kode Anda yang lain (setup RecyclerView, dll)
    }

    // Fungsi untuk handle klik tombol kembali di toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish() // atau onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}