package com.jeff.movieviewer.adapter

import android.content.Context
import android.graphics.Color.blue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jeff.movieviewer.R
import com.jeff.movieviewer.android.base.extension.shortToast
import com.jeff.movieviewer.databinding.SeatItemBinding
import com.jeff.movieviewer.main.seatmap.view.SeatMapActivity
import timber.log.Timber


internal class SeatMapAdapter internal constructor(
    private var context: Context?,
    private var data: List<String>,
    private var availableSeats: List<String>
) : RecyclerView.Adapter<SeatMapAdapter.SeatMapViewHolder>() {
    var selectedSeat : MutableList<String> = mutableListOf()
    internal inner class SeatMapViewHolder(binding: SeatItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var itemLayout: LinearLayout = binding.seatItemLayout
        var txtSeatNumber: TextView = binding.seatNumber

    }

    // inflates the cell layout from xml when needed
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SeatMapViewHolder {
        val binding = DataBindingUtil.inflate<SeatItemBinding>(LayoutInflater.from(p0.context),
            R.layout.seat_item,
            p0,
            false)
        
        return SeatMapViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SeatMapViewHolder,
        position: Int
    ) {
        //holder.txtSeatNumber.text = data[position]
        if (data[position].capitalize() == "A(30)") {
            holder.itemLayout.setBackgroundColor(context!!.getColor(R.color.white))
        } else {
            if (availableSeats.contains(data[position])) {
                holder.itemLayout.setBackgroundColor(context!!.getColor(R.color.light_gray))
            } else {
                holder.itemLayout.setBackgroundColor(context!!.getColor(R.color.blue))
            }
        }


        holder.itemLayout.setOnClickListener {
            Timber.d("==q ${data[position]}")
                if (!selectedSeat.contains(data[position])) {
                    Timber.d("==q RED")
                    setBackgroundColor(holder.itemLayout, R.color.red)
                    selectedSeat.add(data[position])
                } else {
                    Timber.d("==q GRAY")
                    selectedSeat.remove(data[position])
                    setBackgroundColor(holder.itemLayout, R.color.light_gray)
                }
            val context = context as SeatMapActivity
            context.shortToast(data[position])
        }
    }

    private fun setBackgroundColor(layout: LinearLayout, color: Int ) {
        layout.setBackgroundColor(context!!.getColor(color))
    }

    // total number of cells
    override fun getItemCount(): Int {
        return data.size
    }
    
    // convenience method for getting data at click position
    fun getItem(id: Int): String {
        return data[ id]
    }
}