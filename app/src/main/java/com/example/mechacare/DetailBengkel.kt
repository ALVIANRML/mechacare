package com.example.mechacare

import android.content.Intent
import org.osmdroid.config.Configuration
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class DetailBengkel : AppCompatActivity() {
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Konfigurasi OSMDroid
        Configuration.getInstance().load(applicationContext, getSharedPreferences("osmdroid", MODE_PRIVATE))
        setContentView(R.layout.activity_detail_bengkel)

        val arrowback: ImageView = findViewById(R.id.iv_arrowback)

        arrowback.setOnClickListener {
            val intent = Intent(this, PencarianBengkel::class.java)
            startActivity(intent)
        }

        // Ambil data dari Intent
        val nama = intent.getStringExtra("nama") ?: "Nama tidak tersedia"
        val alamat = intent.getStringExtra("alamat") ?: "Alamat tidak tersedia"
        val gambar = intent.getStringExtra("gambar") ?: "" // Gambar bisa berupa URL atau resource ID

        // Tampilkan data ke UI (misalnya di TextView)
        val namaTextView: TextView = findViewById(R.id.tv_nama_bengkel)
        val alamatTextView: TextView = findViewById(R.id.tv_jalan)
        val gambarImageView: ImageView = findViewById(R.id.iv_bengkel)

        namaTextView.text = nama
        alamatTextView.text = alamat
//        // Jika gambar adalah URL, gunakan library seperti Glide untuk memuat gambar
//        if (gambar.isNotEmpty()) {
//            Glide.with(this)
//                .load(gambar)
//                .into(gambarImageView)
//        }

        // Inisialisasi MapView
        mapView = findViewById(R.id.mapView)
        mapView.setMultiTouchControls(true)

        // Lokasi Bengkel
        val bengkelLocation = GeoPoint(intent.getDoubleExtra("latitude", 0.0), intent.getDoubleExtra("longitude", 0.0))
        val bengkelMarker = Marker(mapView).apply {
            position = bengkelLocation
            setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            title = nama
        }

        // Tambahkan marker ke peta
        mapView.overlays.add(bengkelMarker)

        // Atur pusat peta dan zoom
        mapView.controller.setZoom(100.0)
        mapView.controller.setCenter(bengkelLocation)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume() // Aktifkan peta
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause() // Nonaktifkan peta untuk menghemat daya
    }
}
