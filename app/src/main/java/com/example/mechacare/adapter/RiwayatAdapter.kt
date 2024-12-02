package com.example.mechacare.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mechacare.R
import com.example.mechacare.model.Riwayat

class RiwayatAdapter (private val riwayatlist: List<Riwayat>) : RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder>()  {

    inner class RiwayatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val jenisService: TextView = itemView.findViewById(R.id.tv_jenis_service)
        val jenisKendaraan: TextView = itemView.findViewById(R.id.tv_jenis_kendaraan)
        val biayaService: TextView = itemView.findViewById(R.id.tv_biaya_service)
        val tanggalService: TextView = itemView.findViewById(R.id.tv_tanggal_lahir)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.riwayat_item, parent, false)
        return RiwayatViewHolder(view)
    }

    override fun onBindViewHolder(holder: RiwayatViewHolder, position: Int) {
        val berita = riwayatlist[position]
        holder.jenisService.text=berita.jenis_service
        holder.jenisKendaraan.text=berita.jenis_kendaraan
        holder.biayaService.text=berita.biaya_service
        holder.tanggalService.text=berita.tanggal_service
    }

    override fun getItemCount(): Int {
        return riwayatlist.size
    }
}