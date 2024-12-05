package com.example.mechacare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class DataDiri : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_diri)

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance()

        val backButton = findViewById<ImageButton>(R.id.backButton)
        val fullNameEditText = findViewById<EditText>(R.id.fullName)
        val phoneNumberEditText = findViewById<EditText>(R.id.phoneNumber)
        val emailEditText = findViewById<EditText>(R.id.email)
        val nextButton = findViewById<Button>(R.id.nextButton)

        backButton.setOnClickListener {
            // Intent balik ke halaman PilihKendaraanSelectedActivity
            val intent = Intent(this@DataDiri, PilihKendaraanSelectedActivity::class.java)
            startActivity(intent)
        }

        nextButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString().trim()
            val phoneNumber = phoneNumberEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()

            if (fullName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Mohon lengkapi semua data!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save data to Firestore
            val userData = hashMapOf(
                "fullName" to fullName,
                "phoneNumber" to phoneNumber,
                "email" to email
            )

            firestore.collection("users")
                .add(userData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show()

                    // Intent ke halaman PilihPerbaikan
                    val intent = Intent(this@DataDiri, PilihPerbaikan::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Gagal menyimpan data: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
