package com.example.mechacare

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mechacare.adapter.BengkelAdapter
import com.example.mechacare.model.Bengkel

class PencarianBengkel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pencarian_bengkel)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets}
        val items = listOf(
            Bengkel("Bengkel cuplis M2R", "Jl. Abadi No.101-63, Tj. Rejo, Kec. Medan Sunggal, Kota Medan, Sumatera Utara 20122",R.drawable.image_3),
            Bengkel("BENGKEL C.O.D", "Jl. Ring Road No.50 A, Sunggal, Kec. Medan Sunggal, Kota Medan, Sumatera Utara 20122",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Ono Speed", "HJ6Q+7HW, Tj. Sari, Kec. Medan Selayang, Kota Medan, Sumatera Utara 20154",R.drawable.image_3),
            Bengkel("Bengkel Resmi Honda AHASS 16864 DKS Setia Budi", "Komp Setia Budi Point No. A1 -17, Jl. Setia Budi",R.drawable.image_3)
        )
        val recyclerView: RecyclerView = findViewById(R.id.recycleview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BengkelAdapter(items)
    }
    }
