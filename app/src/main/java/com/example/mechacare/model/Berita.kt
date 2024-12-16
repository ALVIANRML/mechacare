package com.example.mechacare.model

import com.example.mechacare.R


data class Berita(
    val imageResId: Int = 0, // Sesuaikan tipe data sesuai dengan Firestore
    val judul: String = "",
    val isi: String = ""
)
{
    constructor() : this(R.drawable.image_3, "","")
}