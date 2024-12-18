package com.example.mechacare

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.text.NumberFormat
import java.util.*

class BuktiBookingActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bukti_booking)

        db = FirebaseFirestore.getInstance()

        // Inisialisasi komponen UI
        val fullNameTextView: TextView = findViewById(R.id.fullNameTextView)
        val phoneNumberTextView: TextView = findViewById(R.id.phoneNumberTextView)
        val emailTextView: TextView = findViewById(R.id.emailTextView)
        val tanggalServiceTextView: TextView = findViewById(R.id.tanggalServiceTextView)
        val serviceLocationTextView: TextView = findViewById(R.id.serviceLocationTextView)
        val varianMotorTextView: TextView = findViewById(R.id.varianMotorTextView)
        val selectedServicesTextView: TextView = findViewById(R.id.selectedServicesTextView)
        val totalPriceTextView: TextView = findViewById(R.id.totalPriceTextView)
        val orderNumberTextView: TextView = findViewById(R.id.orderNumberTextView) // Referensi untuk nomor order

        // Ambil data dari Intent
        val userId = intent.getStringExtra("USER_ID") ?: run {
            showError("USER_ID tidak tersedia")
            return
        }
        val kendaraanId = intent.getStringExtra("KENDARAAN_ID") ?: run {
            showError("KENDARAAN_ID tidak tersedia")
            return
        }

        val bookingId = intent.getStringExtra("BOOKING_ID") ?: run {
            showError("Nomor order tidak tersedia")
            return
        }

        // Tampilkan nomor order
        orderNumberTextView.text = "$bookingId"

        val selectedServices = intent.getStringArrayListExtra("SELECTED_SERVICES") ?: arrayListOf()
        val selectedPrices = intent.getIntegerArrayListExtra("SELECTED_PRICES") ?: arrayListOf()

        // Hitung total harga
        val totalPrice = selectedPrices.sum()
        val formattedPrice = NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(totalPrice)

        // Tampilkan data layanan
        selectedServicesTextView.text = selectedServices.joinToString("\n")
        totalPriceTextView.text = formattedPrice

        // Ambil data pengguna dan kendaraan dari Firestore
        fetchUserData(userId, fullNameTextView, phoneNumberTextView, emailTextView, tanggalServiceTextView, serviceLocationTextView)
        fetchVehicleData(kendaraanId, varianMotorTextView)
    }

    private fun fetchUserData(
        userId: String,
        fullNameTextView: TextView,
        phoneNumberTextView: TextView,
        emailTextView: TextView,
        tanggalServiceTextView: TextView,
        serviceLocationTextView: TextView
    ) {
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val fullName = document.getString("fullName") ?: "Tidak diketahui"
                    val phoneNumber = document.getString("phoneNumber") ?: "Tidak diketahui"
                    val email = document.getString("email") ?: "Tidak diketahui"
                    val tanggalService = document.getString("tanggalService") ?: "Tidak diketahui"
                    val alamat = document.getString("alamat") ?: "Tidak diketahui"
                    val deskripsi = document.getString("deskripsi") ?: "Tidak diketahui"

                    fullNameTextView.text = fullName
                    phoneNumberTextView.text = phoneNumber
                    emailTextView.text = email
                    tanggalServiceTextView.text = tanggalService
                    serviceLocationTextView.text = "$alamat ($deskripsi)"
                } else {
                    showError("Data pengguna tidak ditemukan.")
                }
            }
            .addOnFailureListener { e ->
                showError("Gagal mengambil data pengguna: ${e.message}")
            }
    }

    private fun fetchVehicleData(
        kendaraanId: String,
        varianMotorTextView: TextView
    ) {
        db.collection("kendaraan").document(kendaraanId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val varianMotor = document.getString("varian") ?: "Tidak diketahui"
                    val nomorPolisi = document.getString("nomorPolisi") ?: "Tidak diketahui"

                    varianMotorTextView.text = "$varianMotor ($nomorPolisi)"
                } else {
                    showError("Data kendaraan tidak ditemukan.")
                }
            }
            .addOnFailureListener { e ->
                showError("Gagal mengambil data kendaraan: ${e.message}")
            }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
