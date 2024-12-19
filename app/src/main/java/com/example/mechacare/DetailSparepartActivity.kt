package com.example.mechacare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailSparepartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_sparepart)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val arrowback: ImageView = findViewById(R.id.btn_back)
        arrowback.setOnClickListener {
            finish() // Menutup activity saat tombol back ditekan
        }

        val nama = intent.getStringExtra("nama") ?: "Nama tidak tersedia"
        val harga = intent.getIntExtra("harga", 0)
        val deskripsi = intent.getStringExtra("deskripsi") ?: "Deskripsi tidak tersedia"

        val namaTextView: TextView = findViewById(R.id.tv_detail_deskripsi_1)
        val hargaTextView: TextView = findViewById(R.id.tv_detail_deskripsi)
        val deskripsiTextView: TextView = findViewById(R.id.tv_deskripsi)

        namaTextView.text = nama
        hargaTextView.text = "Rp $harga" // Format harga sesuai kebutuhan
        deskripsiTextView.text = deskripsi

        val beli: Button = findViewById(R.id.btn_buy)

        beli.setOnClickListener{
            val intent = Intent(this, BillingAdressActivity::class.java).apply {
                putExtra("nama", nama)
                putExtra("harga", harga)
                putExtra("deskripsi", deskripsi)
            }

            startActivity(intent)

        }
    }

}
