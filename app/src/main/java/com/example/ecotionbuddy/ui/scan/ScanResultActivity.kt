package com.example.ecotionbuddy.ui.scan

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecotionbuddy.R
import com.example.ecotionbuddy.data.models.RecyclingSuggestion
import com.example.ecotionbuddy.data.models.WasteCategory
import com.example.ecotionbuddy.data.models.WasteDetection
import com.example.ecotionbuddy.databinding.ActivityScanResultBinding

class ScanResultActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityScanResultBinding
    private lateinit var suggestionsAdapter: RecyclingSuggestionsAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupRecyclerView()
        processDetectionResult()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Hasil Pemindaian"
    }
    
    private fun setupRecyclerView() {
        suggestionsAdapter = RecyclingSuggestionsAdapter()
        binding.recyclerViewSuggestions.apply {
            adapter = suggestionsAdapter
            layoutManager = LinearLayoutManager(this@ScanResultActivity)
        }
    }
    
    private fun processDetectionResult() {
        val imageUriString = intent.getStringExtra("image_uri")
        imageUriString?.let { uriString ->
            val imageUri = Uri.parse(uriString)
            binding.imageViewResult.setImageURI(imageUri)
            
            // Simulate AI detection result
            val detectionResult = createSampleDetection()
            displayDetectionResult(detectionResult)
        }
    }
    
    private fun createSampleDetection(): WasteDetection {
        val suggestions = listOf(
            RecyclingSuggestion(
                title = "Botol Plastik Menjadi Pot Tanaman",
                description = "Ubah botol plastik bekas menjadi pot tanaman yang cantik",
                steps = listOf(
                    "Cuci bersih botol plastik",
                    "Potong bagian atas botol",
                    "Buat lubang drainase di bagian bawah",
                    "Hias dengan cat atau kertas warna-warni",
                    "Isi dengan tanah dan tanaman"
                ),
                difficulty = "Mudah",
                estimatedTime = "30 menit",
                materialsNeeded = listOf("Gunting", "Cat", "Tanah", "Bibit tanaman")
            ),
            RecyclingSuggestion(
                title = "Celengan dari Botol Plastik",
                description = "Buat celengan unik dari botol plastik bekas",
                steps = listOf(
                    "Siapkan botol plastik bersih",
                    "Buat celah untuk memasukkan uang",
                    "Hias dengan stiker atau cat",
                    "Tambahkan tutup yang bisa dibuka tutup"
                ),
                difficulty = "Mudah",
                estimatedTime = "20 menit",
                materialsNeeded = listOf("Cutter", "Stiker", "Lem")
            )
        )
        
        return WasteDetection(
            id = "detection_1",
            detectedCategory = WasteCategory.PLASTIC,
            confidence = 0.95f,
            suggestions = suggestions,
            pointsEarned = 50
        )
    }
    
    private fun displayDetectionResult(detection: WasteDetection) {
        binding.apply {
            tvDetectedCategory.text = detection.detectedCategory.displayName
            tvConfidence.text = "${(detection.confidence * 100).toInt()}% yakin"
            tvPointsEarned.text = "+${detection.pointsEarned} Poin"
            
            // Set category color
            val categoryColor = android.graphics.Color.parseColor(detection.detectedCategory.color)
            chipCategory.setChipBackgroundColorResource(android.R.color.transparent)
            chipCategory.setTextColor(categoryColor)
            
            suggestionsAdapter.submitList(detection.suggestions)
        }
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}