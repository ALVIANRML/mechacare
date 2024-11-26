package com.example.mechacare

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mechacare.adapter.BeritaAdapter
import com.example.mechacare.model.Berita

class activity_welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        val items = listOf(
            Berita(R.drawable.image_3, "Pentingnya Mengganti Oli Secara Berkala pada Motor Anda"),
            Berita(R.drawable.image_3, "Cara Menghitung Volume Oli yang Tepat untuk Motor Mati"),
            Berita(R.drawable.image_3, "Ketahui Tanda-tanda Ban Motor Perlu Diganti"),
            Berita(R.drawable.image_3, "Ketahui Tanda-tanda Ban Motor Perlu Diganti")
        )
            val recyclerView: RecyclerView = findViewById(R.id.recycleview)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = BeritaAdapter(items)
    }
    }
