package com.example.crud

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter (
    val tb_data:ArrayList<DataModel.Data>,
    val listener: OnAdapterListener
    )
    : RecyclerView.Adapter<DataAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_data, parent, false)
    )

    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int) {
        val data = tb_data[position]
        holder.textData.text = data.nama_hewan
        holder.itemView.setOnClickListener {
            listener.onClick(data)
        }
        holder.imageDelete.setOnClickListener {
            listener.onDelete(data)
        }
    }

    override fun getItemCount() = tb_data.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textData = view.findViewById<TextView>(R.id.text_data)
        val imageDelete = view.findViewById<ImageView>(R.id.deleteimg)
    }

    public fun setData(Data: List<DataModel.Data>){
        tb_data.clear()
        tb_data.addAll(Data)
        notifyDataSetChanged()
    }
    interface OnAdapterListener{
        fun onClick(Data: DataModel.Data)
        fun onDelete(Data: DataModel.Data)
    }

}