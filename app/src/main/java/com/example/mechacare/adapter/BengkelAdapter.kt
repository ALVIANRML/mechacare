package com.example.mechacare.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mechacare.R
import com.example.mechacare.model.Bengkel

class BengkelAdapter(
    private val bengkelList: List<Bengkel>,
    private val onBengkelClickListener: (Bengkel) -> Unit // Listener klik untuk item
) : RecyclerView.Adapter<BengkelAdapter.BengkelViewHolder>() {

    inner class BengkelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val namaBengkel: TextView = itemView.findViewById(R.id.tv_nama_bengkel)
        val alamatBengkel: TextView = itemView.findViewById(R.id.tv_alamat_bengkel)
        val gambarBengkel: ImageView = itemView.findViewById(R.id.ivbengkel)

        init {
            // Menambahkan klik listener pada itemView
            itemView.setOnClickListener {
                val bengkel = bengkelList[adapterPosition]
                onBengkelClickListener(bengkel)  // Mengirim data Bengkel yang diklik
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BengkelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.bengkel_item, parent, false)
        return BengkelViewHolder(view)
    }

    override fun onBindViewHolder(holder: BengkelViewHolder, position: Int) {
        val bengkel = bengkelList[position]
        holder.namaBengkel.text = bengkel.nama
        holder.alamatBengkel.text = bengkel.alamat
        holder.gambarBengkel.setImageResource(bengkel.gambar)  // Pastikan gambar ada dalam resource
    }

    override fun getItemCount(): Int {
        return bengkelList.size
    }
}
