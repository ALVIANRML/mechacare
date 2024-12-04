package com.example.mechacare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class TambahKendaraanActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_kendaraan)

        db = FirebaseFirestore.getInstance()

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
                // Simpan data kendaraan ke Firebase Firestore
                val kendaraan = hashMapOf(
                    "model" to model,
                    "tahun" to tahun,
                    "varian" to varian,
                    "warna" to warna,
                    "nomorPolisi" to nomor
                )

                db.collection("kendaraan")
                    .add(kendaraan)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this, "Kendaraan berhasil ditambahkan!", Toast.LENGTH_SHORT).show()

                        // Kirim data ke PilihKendaraanSelectedActivity
                        val intent = Intent(this, PilihKendaraanSelectedActivity::class.java)
                        intent.putExtra("KENDARAAN_ID", documentReference.id)  // Kirim ID kendaraan yang baru ditambahkan
                        startActivity(intent)

                        // Tutup aktivitas saat ini
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error adding document: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
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
