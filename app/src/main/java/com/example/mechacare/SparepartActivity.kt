package com.example.mechacare.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mechacare.DetailSparepartActivity
import com.example.mechacare.IsiBerita
import com.example.mechacare.NavbarComponent
import com.example.mechacare.R
import com.example.mechacare.adapter.BeritaAdapter
import com.example.mechacare.adapter.SparepartAdapter
import com.example.mechacare.model.Berita
import com.example.mechacare.model.Sparepart
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class SparepartActivity : AppCompatActivity() {
    private lateinit var adapter: SparepartAdapter
    private lateinit var sparepart: MutableList<Sparepart>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sparepart)

       val db = FirebaseFirestore.getInstance()

        val recyclerView: RecyclerView = findViewById(R.id.rv_sparepart)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        sparepart = mutableListOf()
        adapter = SparepartAdapter(sparepart) { sparepart ->
            val intent = Intent(this, DetailSparepartActivity::class.java).apply {
                putExtra("imageResId", sparepart.foto)
                putExtra("nama", sparepart.nama)
                putExtra("harga", sparepart.harga)
                putExtra("deskripsi", sparepart.deskripsi)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        fetchSparepartData(db)
    }

    private fun fetchSparepartData(db: FirebaseFirestore) {
        db.collection("sparepart")
            .get()
            .addOnSuccessListener { documents ->
                sparepart.clear()
                for (document in documents) {
                    val sparepartObjek = document.toObject(Sparepart::class.java)
                    sparepart.add(sparepartObjek)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error getting documents: ", exception)
            }
        }
}
