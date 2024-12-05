package com.example.mechacare.model

import com.example.mechacare.R
import com.google.type.LatLng

class Bengkel(
    val nama: String,
    val alamat: String,
    val gambar: Int,

){
    constructor() : this("", "", R.drawable.image_3)
}
