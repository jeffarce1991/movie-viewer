package com.jeff.movieviewer.main.seatmap.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.jeff.movieviewer.R
import com.jeff.movieviewer.adapter.SeatMapAdapter
import com.jeff.movieviewer.databinding.ActivitySeatMapBinding
import kotlinx.android.synthetic.main.content_seat_map.view.*


class SeatMapActivity : AppCompatActivity() {

    companion object {
        private var EXTRA_ID = "EXTRA_ID"

        fun getStartIntent(
            context: Context,
            id : Int


        ): Intent {
            return Intent(context, SeatMapActivity::class.java)
                .putExtra(EXTRA_ID, id)
        }
    }

    private lateinit var binding : ActivitySeatMapBinding

    private lateinit var adapter: SeatMapAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_seat_map)

        // data to populate the RecyclerView with
        val data = listOf(
            "A33", "A(30)", "A32", "A31", "A30", "A29", "A28", "A27", "A26", "A25", "A24", "A(30)", "A23", "A22", "A21", "A20", "A19", "A18", "A17", "A16", "A15", "A14", "A13", "A12", "A11", "A10", "a(30)", "A9", "A8", "A7", "A6", "A5", "A4", "A3", "A2", "A1",
            "B34", "B33", "B32", "B31", "B30", "B29", "B28", "B27", "B26", "B25", "a(30)", "B24", "B23", "B22", "B21", "B20", "B19", "B18", "B17", "B16", "B15", "B14", "B13", "B12", "B11", "a(30)", "B10", "B9", "B8", "B7", "B6", "B5", "B4", "B3", "B2", "B1")
        val availableSeats = listOf(
            "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12", "A13", "A14", "A15", "A16", "A17", "A18", "A19", "A20", "A21", "A22", "A23", "A24", "A25", "A26", "A27", "A28", "A29", "A30", "A31", "A32",
            "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12", "B13", "B14", "B15", "B16", "B17", "B18", "B19", "B20", "B21", "B22", "B23", "B24", "B25", "B26", "B27", "B28", "B29", "B30", "B31", "B32", "B33", "B34")

        // set up the RecyclerView
        val numberOfColumns = 36
        binding.root.seatMapRecyclerView.layoutManager = GridLayoutManager(this, numberOfColumns)
        adapter = SeatMapAdapter(this, data, availableSeats)
        binding.root.seatMapRecyclerView.adapter = adapter
    }
}