package com.example.mechacare

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class DataDiri : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_diri)

        // Ambil kendaraanId dari Intent
        val kendaraanId = intent.getStringExtra("KENDARAAN_ID") ?: ""

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance()

        // Inisialisasi elemen tampilan
        val backButton = findViewById<ImageButton>(R.id.backButton)
        val fullNameEditText = findViewById<EditText>(R.id.fullName)
        val phoneNumberEditText = findViewById<EditText>(R.id.phoneNumber)
        val emailEditText = findViewById<EditText>(R.id.email)
        val tanggalEditText = findViewById<EditText>(R.id.tanggal)  // EditText untuk memilih tanggal service
        val alamatEditText = findViewById<EditText>(R.id.alamat)  // EditText untuk alamat
        val deskripsiEditText = findViewById<EditText>(R.id.deskripsi)  // EditText untuk deskripsi
        val nextButton = findViewById<Button>(R.id.nextButton)

        // Membuka activity PilihKendaraanSelectedActivity saat backButton diklik
        backButton.setOnClickListener {
            val intent = Intent(this@DataDiri, PilihKendaraanSelectedActivity::class.java)
            startActivity(intent)
        }

        // Fungsi untuk memilih tanggal service menggunakan DatePickerDialog
        tanggalEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                // Format tanggal dan tampilkan di EditText
                val formattedDate = "$selectedDay-${selectedMonth + 1}-$selectedYear"
                tanggalEditText.setText(formattedDate)
            }, year, month, day)

            datePickerDialog.show()
        }

        // Menangani klik tombol "BERIKUTNYA"
        nextButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString().trim()
            val phoneNumber = phoneNumberEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val tanggalService = tanggalEditText.text.toString().trim()  // Ambil tanggal service
            val alamat = alamatEditText.text.toString().trim()  // Ambil alamat
            val deskripsi = deskripsiEditText.text.toString().trim()  // Ambil deskripsi

            // Validasi form
            if (fullName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || tanggalService.isEmpty() || alamat.isEmpty() || deskripsi.isEmpty()) {
                Toast.makeText(this, "Mohon lengkapi semua data!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simpan data pengguna ke Firestore, termasuk kendaraanId
            val userData = hashMapOf(
                "fullName" to fullName,
                "phoneNumber" to phoneNumber,
                "email" to email,
                "tanggalService" to tanggalService, // Simpan tanggal service
                "alamat" to alamat,  // Simpan alamat
                "deskripsi" to deskripsi, // Simpan deskripsi
                "kendaraanId" to kendaraanId // Simpan kendaraanId untuk referensi kendaraan
            )

            firestore.collection("users")
                .add(userData)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show()

                    // Kirimkan data ke PilihPerbaikan terlebih dahulu
                    val intent = Intent(this@DataDiri, PilihPerbaikan::class.java)
                    intent.putExtra("USER_ID", documentReference.id) // Kirim ID pengguna yang baru ditambahkan
                    intent.putExtra("KENDARAAN_ID", kendaraanId) // Kirim kendaraanId
                    intent.putExtra("FULL_NAME", fullName) // Kirim nama lengkap pengguna
                    intent.putExtra("PHONE_NUMBER", phoneNumber) // Kirim nomor telepon pengguna
                    intent.putExtra("EMAIL", email) // Kirim email pengguna
                    intent.putExtra("TANGGAL_SERVICE", tanggalService) // Kirim tanggal service
                    intent.putExtra("ALAMAT", alamat) // Kirim alamat
                    intent.putExtra("DESKRIPSI", deskripsi) // Kirim deskripsi
                    startActivity(intent)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Gagal menyimpan data: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
