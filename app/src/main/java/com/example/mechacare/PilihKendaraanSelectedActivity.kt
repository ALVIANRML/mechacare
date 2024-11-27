package com.example.mechacare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PilihKendaraanSelectedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_kendaraan_selected)

        // Inisialisasi elemen tampilan
        val pilihKendaraanLain = findViewById<TextView>(R.id.btnPilihKendaraanLain)
        val btnBerikutnya = findViewById<Button>(R.id.btnBerikutnya)
        val tvMotorInfo = findViewById<TextView>(R.id.tvMotorInfo)
        val backButton = findViewById<ImageView>(R.id.btnBack)

        // Ambil data yang dikirim melalui Intent
        val varianMotor = intent.getStringExtra("VARIAN_MOTOR") ?: "Varian tidak tersedia"
        val nomorPolisi = intent.getStringExtra("NOMOR_POLISI") ?: "Nomor tidak tersedia"

        // Tampilkan informasi varian motor dan nomor polisi
        tvMotorInfo.text = "$varianMotor\n$nomorPolisi"

        // Tombol Pilih Kendaraan Lain
        pilihKendaraanLain.setOnClickListener {
            // Kembali ke Pilih Kendaraan (Sebelum Memilih)
            val intent = Intent(this, PilihKendaraanActivity::class.java)
            startActivity(intent)
        }

        // Tombol Berikutnya
        btnBerikutnya.setOnClickListener {
            // Lanjutkan ke halaman utama atau tujuan berikutnya
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Tombol Kembali
        backButton.setOnClickListener {
            finish()
        }
    }
}
