package com.example.mechacare.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mechacare.R
import com.example.mechacare.model.Berita

class BeritaAdapter (private val beritaList: List<Berita>) : RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {

    inner class BeritaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val gambarBerita:ImageView = itemView.findViewById(R.id.ivberita)
        val textBerita: TextView = itemView.findViewById(R.id.tv_berita)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.berita_item, parent, false)
        return BeritaViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val berita = beritaList[position]
        holder.gambarBerita.setImageResource(berita.imageResId)
        holder.textBerita.text=berita.isiBerita
    }

    override fun getItemCount(): Int {
        return beritaList.size
    }
}