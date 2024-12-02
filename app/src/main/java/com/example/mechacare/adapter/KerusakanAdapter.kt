package com.example.mechacare.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mechacare.R
import com.example.mechacare.model.Kerusakan

class KerusakanAdapter (private val kerusakanlist: List<Kerusakan>) : RecyclerView.Adapter<KerusakanAdapter.KerusakanViewHolder>()  {
    inner class KerusakanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val jeniskerusakan: TextView = itemView.findViewById(R.id.tv_jenis_kerusakan)
        val hargakerusakan: TextView = itemView.findViewById(R.id.tv_harga_kerusakan)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KerusakanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.kerusakan_item, parent, false)
        return KerusakanViewHolder(view)
    }

    override fun onBindViewHolder(holder: KerusakanViewHolder, position: Int) {
        val berita = kerusakanlist[position]
        holder.jeniskerusakan.text=berita.kerusakan
        holder.hargakerusakan.text=berita.harga
    }

    override fun getItemCount(): Int {
        return kerusakanlist.size
    }
}