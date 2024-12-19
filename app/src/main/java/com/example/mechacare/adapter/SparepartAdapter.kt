package com.example.mechacare.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mechacare.R
import com.example.mechacare.model.Sparepart

class SparepartAdapter(
    private val spareparts: List<Sparepart>,
    private val onSparepartClickListener: (Sparepart) -> Unit
) : RecyclerView.Adapter<SparepartAdapter.SparepartViewHolder>() {

    inner class SparepartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_sparepart)
        val namaSparepart: TextView = itemView.findViewById(R.id.tv_nama_sparepart)
//        val deskripsiSparepart: TextView = itemView.findViewById(R.id.tv_deskripsi_sparepart)

        init {
            // Menambahkan klik listener pada itemView
            itemView.setOnClickListener {
                val sparepart = spareparts[adapterPosition]
                onSparepartClickListener(sparepart) // Mengirim data Sparepart yang diklik
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SparepartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sparepart_item, parent, false)
        return SparepartViewHolder(view)
    }

    override fun onBindViewHolder(holder: SparepartViewHolder, position: Int) {
        val sparepart = spareparts[position]

        // Menampilkan data ke ViewHolder
        Glide.with(holder.imageView.context)
            .load(sparepart.foto) // Menggunakan Glide untuk memuat gambar dari URL
            .placeholder(R.drawable.gear) // Placeholder saat gambar belum dimuat
            .into(holder.imageView)

        holder.namaSparepart.text = sparepart.nama
//        holder.deskripsiSparepart.text = sparepart.deskripsi
    }

    override fun getItemCount(): Int = spareparts.size
}
