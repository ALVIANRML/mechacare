package com.example.mechacare

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mechacare.adapter.BeritaAdapter
import com.example.mechacare.model.Bengkel
import com.example.mechacare.model.Berita
import com.example.mechacare.ui.SparepartActivity
import com.google.firebase.firestore.FirebaseFirestore

class activity_welcome : AppCompatActivity() {
    private lateinit var adapter: BeritaAdapter
    private lateinit var beritaList: MutableList<Berita>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Gambar "Home Service"
        val ivHomeService = findViewById<ImageView>(R.id.iv_homeService)
        ivHomeService.setOnClickListener {
            val intent = Intent(this, PilihKendaraanActivity::class.java)
            startActivity(intent)
        }

        // Gambar "Sparepart" untuk Beli Sparepart
        val ivJualBeliSparepart = findViewById<ImageView>(R.id.iv_jualBeliSparepart)
        ivJualBeliSparepart.setOnClickListener {
            val intent = Intent(this, SparepartActivity::class.java)
            startActivity(intent)
        }

        // Gambar "riwayatService"
        val ivRiwayatService = findViewById<ImageView>(R.id.iv_riwayatService)
        ivRiwayatService.setOnClickListener {
            val intent = Intent(this, RiwayatService::class.java)
            startActivity(intent)
        }

        val db = FirebaseFirestore.getInstance()

        // Setup RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recycleview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        beritaList = mutableListOf()
        adapter = BeritaAdapter(beritaList) { berita ->
            val intent = Intent(this, IsiBerita::class.java).apply {
                putExtra("imageResId", berita.imageResId)
                putExtra("judulBerita", berita.judul)
                putExtra("isiBerita", berita.isi)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        fetchBeritaData(db)



        val navbar = findViewById<View>(R.id.navbar)
        NavbarComponent(this, navbar)
    }
    private fun fetchBeritaData(db: FirebaseFirestore) {
        db.collection("berita")
            .get()
            .addOnSuccessListener { documents ->
                beritaList.clear()
                for (document in documents) {
                    val berita = document.toObject(Berita::class.java)
                    beritaList.add(berita)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error getting documents: ", exception)
            }}
}
