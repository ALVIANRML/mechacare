package com.example.mechacare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mechacare.adapter.KerusakanAdapter
import com.example.mechacare.model.Kerusakan
import com.google.firebase.firestore.FirebaseFirestore

class PilihPerbaikan : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private val selectedServices = mutableListOf<String>()
    private val selectedPrices = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pilih_perbaikan)

        // Inisialisasi Firestore
        firestore = FirebaseFirestore.getInstance()

        // Data untuk RecyclerView (Layanan Perbaikan)
        val items = listOf(
            Kerusakan("Ganti Oli Mesin", "Rp 50.000"),
            Kerusakan("Servis Karburator", "Rp 100.000"),
            Kerusakan("Ganti Busi", "Rp 30.000"),
            Kerusakan("Cek Kompresi Mesin", "Rp 70.000"),
            Kerusakan("Perbaiki Rem Depan", "Rp 80.000"),
            Kerusakan("Ganti Kampas Rem Belakang", "Rp 60.000"),
            Kerusakan("Servis Shockbreaker", "Rp 150.000"),
            Kerusakan("Ganti Rantai & Gear", "Rp 120.000"),
            Kerusakan("Perbaiki Lampu Depan", "Rp 40.000"),
            Kerusakan("Ganti Filter Udara", "Rp 50.000"),
            Kerusakan("Cek Kelistrikan", "Rp 90.000"),
            Kerusakan("Servis Karburator & Mesin", "Rp 200.000"),
            Kerusakan("Ganti Ban Depan", "Rp 150.000"),
            Kerusakan("Ganti Ban Belakang", "Rp 180.000"),
            Kerusakan("Perbaiki Setelan Rantai", "Rp 30.000"),
            Kerusakan("Perbaiki Sistem Pengapian", "Rp 100.000"),
            Kerusakan("Ganti Kabel Rem", "Rp 25.000"),
            Kerusakan("Ganti Spion", "Rp 40.000"),
            Kerusakan("Servis Mesin Roda", "Rp 250.000"),
            Kerusakan("Cek Kaki-Kaki Motor", "Rp 60.000")
        )

        // RecyclerView untuk menampilkan layanan perbaikan
        val recyclerView: RecyclerView = findViewById(R.id.recycleview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = KerusakanAdapter(items, object : KerusakanAdapter.OnServiceSelectedListener {
            override fun onServiceSelected(service: Kerusakan, isSelected: Boolean) {
                // Membersihkan string harga sebelum parsing
                val harga = service.harga.replace("Rp", "")      // Hapus simbol "Rp"
                    .replace(".", "")     // Hapus titik sebagai pemisah ribuan
                    .trim()               // Hilangkan spasi berlebih

                val hargaInt = harga.toIntOrNull() ?: 0          // Ubah ke integer, fallback ke 0 jika gagal

                if (isSelected) {
                    selectedServices.add(service.kerusakan)
                    selectedPrices.add(hargaInt)
                } else {
                    selectedServices.remove(service.kerusakan)
                    selectedPrices.remove(hargaInt)
                }
            }

        })


        // Tombol untuk lanjut ke halaman BuktiBookingActivity
        val nextButton = findViewById<Button>(R.id.button_next)
        nextButton.setOnClickListener {
            if (selectedServices.isNotEmpty()) {
                // Ambil data user dan kendaraan dari Intent
                val userId = intent.getStringExtra("USER_ID") ?: ""
                val kendaraanId = intent.getStringExtra("KENDARAAN_ID") ?: ""

                // Simpan data layanan yang dipilih ke Firestore
                val serviceData = hashMapOf(
                    "userId" to userId,
                    "kendaraanId" to kendaraanId,
                    "selectedServices" to selectedServices,
                    "selectedPrices" to selectedPrices
                )

                firestore.collection("bookings")
                    .add(serviceData)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this, "Layanan berhasil disimpan!", Toast.LENGTH_SHORT).show()

                        // Kirim data ke BuktiBookingActivity
                        val intent = Intent(this, BuktiBookingActivity::class.java)
                        intent.putExtra("USER_ID", userId) // Kirim ID pengguna yang baru ditambahkan
                        intent.putExtra("KENDARAAN_ID", kendaraanId) // Kirim kendaraanId
                        intent.putExtra("BOOKING_ID", documentReference.id)  // Menambahkan ID booking sebagai nomor order

                        intent.putStringArrayListExtra("SELECTED_SERVICES", ArrayList(selectedServices))
                        intent.putIntegerArrayListExtra("SELECTED_PRICES", ArrayList(selectedPrices))

                        startActivity(intent)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Gagal menyimpan layanan: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Pilih minimal satu layanan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
