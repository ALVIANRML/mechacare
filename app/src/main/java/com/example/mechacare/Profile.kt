package com.example.mechacare

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val firestore = FirebaseFirestore.getInstance()
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            val uid = user.uid
            val userDoc = firestore.collection("users").document(uid)

            userDoc.get().addOnSuccessListener { document ->
                if (document.exists()) {
                    val name = document.getString("namaPengguna") ?: "Nama tidak tersedia"
                    val email = document.getString("email") ?: "Email tidak tersedia"
                    val jenisKelamin = document.getBoolean("jenisKelamin")?.let {
                        if (it) "Laki-laki" else "Perempuan"
                    } ?: "Jenis kelamin belum di isi"
                    val nomorTelepon = document.getString("nomorTelepon") ?: "Nomor Telepon belum di isi"
                    val tanggalLahir = document.getString("tanggalLahir") ?: "Nomor Telepon belum di isi"

                    // Display data
                    val textViewName = findViewById<TextView>(R.id.tv_nama)
                    val textViewEmail = findViewById<TextView>(R.id.tv_email)
                    val textViewJenisKelamin = findViewById<TextView>(R.id.tv_jenis_kelamin)
                    val textViewNomortelepon = findViewById<TextView>(R.id.tv_telepon)
                    val textViewTanggalLahir = findViewById<TextView>(R.id.tv_tanggal_lahir)

                    textViewName.text = name
                    textViewEmail.text = email
                    textViewJenisKelamin.text = jenisKelamin
                    textViewNomortelepon.text = nomorTelepon
                    textViewTanggalLahir.text = tanggalLahir

                } else {
                    Toast.makeText(this, "Data tidak ditemukan!", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(this, "Gagal mengambil data: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Pengguna tidak ditemukan!", Toast.LENGTH_SHORT).show()
        }
    }
}