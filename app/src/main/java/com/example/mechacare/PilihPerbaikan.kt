package com.example.mechacare

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mechacare.adapter.BeritaAdapter
import com.example.mechacare.adapter.KerusakanAdapter
import com.example.mechacare.model.Berita
import com.example.mechacare.model.Kerusakan

class PilihPerbaikan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pilih_perbaikan)

        // Data untuk RecyclerView (Berita Terkini)
        val items = listOf(
            Kerusakan("Perbaiki CTV", "Rp 100.000"),
            Kerusakan("Ganti Kabel Listrik", "Rp 75.000"),
            Kerusakan("Servis AC", "Rp 200.000"),
            Kerusakan("Perbaiki Pintu", "Rp 50.000"),
            Kerusakan("Ganti Bohlam", "Rp 25.000"),
            Kerusakan("Perbaiki Jaringan Internet", "Rp 150.000"),
            Kerusakan("Ganti Keran Air", "Rp 80.000"),
            Kerusakan("Servis Lift", "Rp 500.000"),
            Kerusakan("Perbaiki Kamera CCTV", "Rp 300.000"),
            Kerusakan("Cat Ulang Dinding", "Rp 250.000"),
            Kerusakan("Servis Mesin Genset", "Rp 400.000"),
            Kerusakan("Perbaiki Plafon Bocor", "Rp 350.000"),
            Kerusakan("Ganti Kunci Pintu", "Rp 120.000"),
            Kerusakan("Servis Mesin Pendingin", "Rp 220.000"),
            Kerusakan("Ganti Panel Listrik", "Rp 450.000"),
            Kerusakan("Perbaiki Pompa Air", "Rp 320.000"),
            Kerusakan("Pasang Stop Kontak Baru", "Rp 60.000"),
            Kerusakan("Servis Alat Pemadam Api", "Rp 180.000"),
            Kerusakan("Ganti Kaca Jendela", "Rp 300.000"),
            Kerusakan("Perbaiki Sistem Pemanas", "Rp 270.000"),
            Kerusakan("Perbaiki Atap Bocor", "Rp 400.000"),
            Kerusakan("Servis Pintu Otomatis", "Rp 550.000"),
            Kerusakan("Ganti Remote AC", "Rp 90.000"),
            Kerusakan("Perbaiki Tirai Otomatis", "Rp 130.000")
        )

        val recyclerView: RecyclerView = findViewById(R.id.recycleview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = KerusakanAdapter(items)
        }
    }
