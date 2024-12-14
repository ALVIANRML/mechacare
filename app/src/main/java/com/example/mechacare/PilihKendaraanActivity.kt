package com.example.mechacare

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PilihKendaraanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_kendaraan)

        // Tombol Pilih Kendaraan
        val pilihKendaraan = findViewById<TextView>(R.id.btnPilihKendaraan)
        pilihKendaraan.setOnClickListener {
            // Intent ke TambahKendaraanActivity
            val intent = Intent(this, TambahKendaraanActivity::class.java)
            startActivity(intent)
        }

        val navbar = findViewById<View>(R.id.navbar)
        NavbarComponent(this, navbar)
    }
}
