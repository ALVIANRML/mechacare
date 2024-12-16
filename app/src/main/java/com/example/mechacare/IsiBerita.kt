package com.example.mechacare

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class IsiBerita : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_isi_berita)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val arrowback: ImageView = findViewById(R.id.iv_arrowback)

        arrowback.setOnClickListener {
            val intent = Intent(this, activity_welcome::class.java)
            startActivity(intent)
        }
        val imageResId = intent.getIntExtra("imageResId", 0)
        val judulBerita = intent.getStringExtra("judulBerita")
        val isiBerita = intent.getStringExtra("isiBerita")

        val ivBerita: ImageView = findViewById(R.id.iv_berita)
        val tvJudul: TextView = findViewById(R.id.tv_berita)
        val tvIsi: TextView = findViewById(R.id.tv_isi_berita)

        ivBerita.setImageResource(imageResId)
        tvJudul.text = judulBerita
        tvIsi.text = isiBerita

    }
}