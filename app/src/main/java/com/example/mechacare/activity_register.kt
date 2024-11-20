package com.example.mechacare

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // Atur teks "MechaCare" pada TextView dengan ID tv_logo
        val textViewLogo = findViewById<TextView>(R.id.tv_logo)
        val spannableLogo = SpannableString("MechaCare")
        spannableLogo.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.yellow)),
            0, 5, // "Mecha"
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableLogo.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.white)),
            5, 9, // "Care"
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textViewLogo.text = spannableLogo

        // Atur teks "Belum punya akun? Klik Untuk Daftar" pada TextView dengan ID daftar
        val textViewDaftar = findViewById<TextView>(R.id.daftar)
        val spannableDaftar = SpannableString("Sudah punya akun? Klik Untuk Masuk")
        spannableDaftar.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.blue)),
            17, spannableDaftar.length, // "Klik Untuk Daftar"
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textViewDaftar.text = spannableDaftar
    }
}
