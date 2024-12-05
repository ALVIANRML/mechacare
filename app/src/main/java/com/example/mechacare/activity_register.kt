package com.example.mechacare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class activity_register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val inputNamaPengguna = findViewById<EditText>(R.id.nama_pengguna)
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val inputPassword = findViewById<EditText>(R.id.inputPassword)
        val konfirmasiPassword = findViewById<EditText>(R.id.konfirmasiPassword)
        val btnRegister = findViewById<Button>(R.id.btnLogin)

        val daftar: TextView = findViewById(R.id.daftar)
        daftar.setOnClickListener{
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }



        // Register Button Click
        btnRegister.setOnClickListener {
            val namaPengguna = inputNamaPengguna.text.toString().trim()
            val email = inputEmail.text.toString().trim()
            val password = inputPassword.text.toString().trim()
            val confirmPassword = konfirmasiPassword.text.toString().trim()

            // Validasi input
            if (namaPengguna.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Password tidak cocok!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Firebase Authentication untuk registrasi
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()

                        // Kirim nama pengguna ke halaman login
                        val userIdu = auth.currentUser?.uid
                        val userData = hashMapOf(
                            "namaPengguna" to namaPengguna,
                            "email" to email
                        )
                        firestore.collection("users").document(userIdu ?: "")
                            .set(userData)

                        val intent = Intent(this, login::class.java)
                        intent.putExtra("USER_NAME", namaPengguna)
                        startActivity(intent)
                        finish()  // Tutup halaman registrasi
                    } else {
                        Toast.makeText(this, "Registrasi Gagal: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
