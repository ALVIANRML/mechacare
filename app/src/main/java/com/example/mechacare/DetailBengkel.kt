package com.example.mechacare

import org.osmdroid.config.Configuration
import android.os.Bundle
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

        // Inisialisasi MapView
        mapView = findViewById(R.id.mapView)
        mapView.setMultiTouchControls(true)

        // Lokasi Anda
        val myLocation = GeoPoint(3.562780, 98.660027
            ) // Contoh: Jakarta
        val myMarker = Marker(mapView).apply {
            position = myLocation
            setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            title = "Lokasi Anda"
        }

        // Lokasi Tujuan
        val destinationLocation = GeoPoint(3.56283733451413, 98.63853458829509) // Contoh: Tangerang
        val destinationMarker = Marker(mapView).apply {
            position = destinationLocation
            setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            title = "Lokasi Tujuan"
        }

        // Tambahkan marker ke peta
        mapView.overlays.add(myMarker)
        mapView.overlays.add(destinationMarker)

        // Atur pusat peta dan zoom
        mapView.controller.setZoom(100.0)
        mapView.controller.setCenter(myLocation)
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
