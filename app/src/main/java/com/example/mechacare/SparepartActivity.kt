package com.example.mechacare.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mechacare.NavbarComponent
import com.example.mechacare.R
import com.example.mechacare.adapter.SparepartAdapter
import com.example.mechacare.model.Sparepart

class SparepartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sparepart)

        // Daftar sparepart
        val spareparts = listOf(
            Sparepart(R.drawable.aki, "Aki Motor"),
            Sparepart(R.drawable.mur, "Mur Kopling"),
            Sparepart(R.drawable.spion, "Spion Motor"),
            Sparepart(R.drawable.gear, "Gear Rantai Motor"),
            Sparepart(R.drawable.spull, "Spull Assy"),
            Sparepart(R.drawable.knalpot, "Knalpot Motor"),
        )
        val recyclerView: RecyclerView = findViewById(R.id.rv_sparepart)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = SparepartAdapter(spareparts)

    }
}
