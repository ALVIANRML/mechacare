package com.example.mechacare

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mechacare.adapter.BeritaAdapter
import com.example.mechacare.adapter.RiwayatAdapter
import com.example.mechacare.model.Riwayat

class RiwayatService : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_riwayat_service)

        val items = listOf(
            Riwayat("Ganti Oli", "Honda Vario", "Rp. 100.000", "20-02-2024"),
            Riwayat("Servis Rutin", "Yamaha NMAX", "Rp. 300.000", "18-01-2024"),
            Riwayat("Ganti Ban", "Suzuki Satria", "Rp. 400.000", "10-03-2024"),
            Riwayat("Cuci Motor", "Kawasaki Ninja", "Rp. 50.000", "15-02-2024"),
            Riwayat("Ganti Rem", "Honda Beat", "Rp. 150.000", "05-02-2024"),
            Riwayat("Tune Up", "Yamaha Aerox", "Rp. 250.000", "01-04-2024"),
            Riwayat("Perbaikan Lampu", "Vespa Sprint", "Rp. 75.000", "28-03-2024"),
            Riwayat("Isi Angin", "Suzuki Smash", "Rp. 10.000", "22-01-2024"),
            Riwayat("Servis Karburator", "Honda Scoopy", "Rp. 200.000", "12-02-2024"),
            Riwayat("Perbaikan Mesin", "Yamaha XSR", "Rp. 1.000.000", "30-03-2024"),
            Riwayat("Ganti Kampas Kopling", "Kawasaki KLX", "Rp. 450.000", "17-03-2024"),
            Riwayat("Polish Body", "Honda Supra X", "Rp. 100.000", "14-02-2024"),
            Riwayat("Ganti Rantai", "Suzuki Thunder", "Rp. 300.000", "05-01-2024"),
            Riwayat("Perbaikan Shockbreaker", "Yamaha Jupiter", "Rp. 200.000", "08-03-2024"),
            Riwayat("Servis Elektronik", "Vespa LX", "Rp. 150.000", "21-02-2024")
        )
        val recyclerView: RecyclerView = findViewById(R.id.recycleview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RiwayatAdapter(items)
    }
}