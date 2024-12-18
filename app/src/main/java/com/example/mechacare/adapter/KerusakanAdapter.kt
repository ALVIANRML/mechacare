package com.example.mechacare.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mechacare.R
import com.example.mechacare.model.Kerusakan

class KerusakanAdapter(
    private var kerusakanlist: List<Kerusakan>,
    private val listener: OnServiceSelectedListener
) : RecyclerView.Adapter<KerusakanAdapter.KerusakanViewHolder>() {

    private val selectedStates = MutableList(kerusakanlist.size) { false }

    interface OnServiceSelectedListener {
        fun onServiceSelected(service: Kerusakan, isSelected: Boolean)
    }

    inner class KerusakanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jeniskerusakan: TextView = itemView.findViewById(R.id.tv_jenis_kerusakan)
        val hargakerusakan: TextView = itemView.findViewById(R.id.tv_harga_kerusakan)
        val serviceCheckBox: CheckBox = itemView.findViewById(R.id.serviceCheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KerusakanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.kerusakan_item, parent, false)
        return KerusakanViewHolder(view)
    }

    override fun onBindViewHolder(holder: KerusakanViewHolder, position: Int) {
        val kerusakan = kerusakanlist[position]
        holder.jeniskerusakan.text = kerusakan.kerusakan
        holder.hargakerusakan.text = kerusakan.harga

        holder.serviceCheckBox.setOnCheckedChangeListener(null)
        holder.serviceCheckBox.isChecked = selectedStates[position]
        holder.serviceCheckBox.setOnCheckedChangeListener { _, isChecked ->
            selectedStates[position] = isChecked
            listener.onServiceSelected(kerusakan, isChecked)
            Log.d("KerusakanAdapter", "Service: ${kerusakan.kerusakan}, Selected: $isChecked")
        }
    }

    override fun getItemCount(): Int {
        return kerusakanlist.size
    }

}
