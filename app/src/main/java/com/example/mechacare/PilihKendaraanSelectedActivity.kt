package com.example.mechacare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class PilihKendaraanSelectedActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_kendaraan_selected)

        db = FirebaseFirestore.getInstance()

        // Inisialisasi elemen tampilan
        val pilihKendaraanLain = findViewById<TextView>(R.id.btnPilihKendaraanLain)
        val btnBerikutnya = findViewById<Button>(R.id.btnBerikutnya)
        val tvMotorInfo = findViewById<TextView>(R.id.tvMotorInfo)
        val backButton = findViewById<ImageView>(R.id.btnBack)

        // Ambil data yang dikirim melalui Intent
        val kendaraanId = intent.getStringExtra("KENDARAAN_ID") ?: ""

        if (kendaraanId.isNotEmpty()) {
            // Ambil data kendaraan dari Firebase menggunakan ID yang diterima
            db.collection("kendaraan").document(kendaraanId)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val varianMotor = document.getString("varian") ?: "Varian tidak tersedia"
                        val nomorPolisi = document.getString("nomorPolisi") ?: "Nomor tidak tersedia"

                        // Tampilkan informasi varian motor dan nomor polisi
                        tvMotorInfo.text = "$varianMotor\n$nomorPolisi"
                    } else {
                        tvMotorInfo.text = "Data kendaraan tidak ditemukan"
                    }
                }
                .addOnFailureListener { e ->
                    tvMotorInfo.text = "Gagal mengambil data: ${e.message}"
                }
        }

        // Tombol Pilih Kendaraan Lain
        pilihKendaraanLain.setOnClickListener {
            // Kembali ke Pilih Kendaraan (Sebelum Memilih)
            val intent = Intent(this, PilihKendaraanActivity::class.java)
            startActivity(intent)
        }

        // Tombol Berikutnya
        btnBerikutnya.setOnClickListener {
            // Kirim kendaraanId ke halaman DataDiri
            val intent = Intent(this, DataDiri::class.java)
            intent.putExtra("KENDARAAN_ID", kendaraanId)
            startActivity(intent)
        }


        // Tombol Kembali
        backButton.setOnClickListener {
            finish()
        }
    }
}
