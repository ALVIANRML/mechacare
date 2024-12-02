package com.example.mechacare.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mechacare.R
import com.example.mechacare.model.Sparepart

class SparepartAdapter(private val sparepart: List<Sparepart>) : RecyclerView.Adapter<SparepartAdapter.SparepartViewHolder>() {

    inner class SparepartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_sparepart)
        val textView: TextView = itemView.findViewById(R.id.tv_deskripsi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SparepartViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.sparepart_item, parent, false)
        return SparepartViewHolder(view)
    }

    override fun onBindViewHolder(holder: SparepartViewHolder, position: Int) {
        val sparepart = sparepart[position]
        holder.imageView.setImageResource(sparepart.imageadd)
        holder.textView.text = sparepart.deskripsi
    }
    override fun getItemCount(): Int {
        return sparepart.size
    }
}
