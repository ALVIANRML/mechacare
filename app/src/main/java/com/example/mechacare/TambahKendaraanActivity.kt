package com.example.mechacare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TambahKendaraanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_kendaraan)

        // Inisialisasi komponen
        val modelMotor = findViewById<EditText>(R.id.modelMotor)
        val tahunProduksi = findViewById<EditText>(R.id.tahunProduksi)
        val varianMotor = findViewById<EditText>(R.id.varianMotor)
        val warnaMotor = findViewById<EditText>(R.id.warnaMotor)
        val nomorPolisi = findViewById<EditText>(R.id.nomorPolisi)
        val btnTambah = findViewById<Button>(R.id.btnTambahKendaraan)
        val btnBatal = findViewById<Button>(R.id.btnBatal)

        // Tambahkan Kendaraan
        btnTambah.setOnClickListener {
            val model = modelMotor.text.toString()
            val tahun = tahunProduksi.text.toString()
            val varian = varianMotor.text.toString()
            val warna = warnaMotor.text.toString()
            val nomor = nomorPolisi.text.toString()

            if (model.isNotEmpty() && tahun.isNotEmpty() && varian.isNotEmpty() && warna.isNotEmpty() && nomor.isNotEmpty()) {
                // Berhasil menambahkan kendaraan
                Toast.makeText(this, "Kendaraan berhasil ditambahkan!", Toast.LENGTH_SHORT).show()

                // Kirim data ke PilihKendaraanSelectedActivity
                val intent = Intent(this, PilihKendaraanSelectedActivity::class.java)
                intent.putExtra("VARIAN_MOTOR", varian)
                intent.putExtra("NOMOR_POLISI", nomor)
                startActivity(intent)

                // Tutup aktivitas saat ini
                finish()
            } else {
                // Tampilkan pesan error
                Toast.makeText(this, "Lengkapi semua data!", Toast.LENGTH_SHORT).show()
            }
        }

        // Batalkan
        btnBatal.setOnClickListener {
            finish()
        }
    }
}
