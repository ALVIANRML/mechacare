package com.example.mechacare

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mechacare.adapter.BeritaAdapter
import com.example.mechacare.model.Berita

class activity_welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)

        // Data untuk RecyclerView (Berita Terkini)
        val items = listOf(
            Berita(R.drawable.image_3, "Pentingnya Mengganti Oli Secara Berkala pada Motor Anda"),
            Berita(R.drawable.image_3, "Cara Menghitung Volume Oli yang Tepat untuk Motor Mati"),
            Berita(R.drawable.image_3, "Ketahui Tanda-tanda Ban Motor Perlu Diganti"),
            Berita(R.drawable.image_3, "Ketahui Tanda-tanda Ban Motor Perlu Diganti")
        )
        val recyclerView: RecyclerView = findViewById(R.id.recycleview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BeritaAdapter(items)

        // Gambar "Home Service"
        val ivHomeService = findViewById<ImageView>(R.id.iv_homeService)
        ivHomeService.setOnClickListener {
            val intent = Intent(this, PilihKendaraanActivity::class.java)
            startActivity(intent)
        }
    }
}
