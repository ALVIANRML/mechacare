package com.example.mechacare

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mechacare.adapter.BengkelAdapter
import com.example.mechacare.model.Bengkel
import com.google.firebase.firestore.FirebaseFirestore

class PencarianBengkel : AppCompatActivity() {

    private lateinit var adapter: BengkelAdapter
    private lateinit var bengkelList: MutableList<Bengkel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pencarian_bengkel)

        val arrowBack = findViewById<ImageView>(R.id.iv_arrowback)

            arrowBack.setOnClickListener {
                val intent = Intent(this, activity_welcome::class.java)
                startActivity(intent)

            }

        // Set padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Firestore
        val db = FirebaseFirestore.getInstance()

        // RecyclerView setup
        val recyclerView: RecyclerView = findViewById(R.id.recycleview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        bengkelList = mutableListOf()

        // Set adapter dengan listener
        adapter = BengkelAdapter(bengkelList) { bengkel ->
            // Ketika item diklik, kirim data ke DetailBengkel
            val intent = Intent(this, DetailBengkel::class.java).apply {
                putExtra("nama", bengkel.nama)
                putExtra("alamat", bengkel.alamat)
                putExtra("gambar", bengkel.gambar) // Jika gambar adalah resource ID
                // Tambahkan koordinat atau data lainnya sesuai kebutuhan
                // putExtra("latitude", bengkel.latitude)
                // putExtra("longitude", bengkel.longitude)
            }
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        // Fetch all data initially
        fetchBengkelData(db)

        // Handle search input
        val searchEditText = findViewById<EditText>(R.id.search_input)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                if (query.isNotEmpty()) {
                    searchBengkel(db, query)
                } else {
                    // Fetch all data again when search is empty
                    fetchBengkelData(db)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        val navbar = findViewById<View>(R.id.navbar)
        NavbarComponent(this, navbar)
    }

    private fun fetchBengkelData(db: FirebaseFirestore) {
        db.collection("bengkel")
            .get()
            .addOnSuccessListener { documents ->
                bengkelList.clear()
                for (document in documents) {
                    val bengkel = document.toObject(Bengkel::class.java)
                    bengkelList.add(bengkel)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error getting documents: ", exception)
            }
    }

    private fun searchBengkel(db: FirebaseFirestore, query: String) {
        db.collection("bengkel")
            .orderBy("nama") // Pastikan "nama" ada di Firestore Index
            .startAt(query)
            .endAt("$query\uf8ff")
            .get()
            .addOnSuccessListener { documents ->
                bengkelList.clear()
                for (document in documents) {
                    val bengkel = document.toObject(Bengkel::class.java)
                    bengkelList.add(bengkel)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error searching documents: ", exception)
            }
    }

}
