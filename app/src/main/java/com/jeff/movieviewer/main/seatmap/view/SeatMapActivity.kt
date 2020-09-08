package com.jeff.movieviewer.main.seatmap.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.jeff.movieviewer.R
import com.jeff.movieviewer.adapter.SeatMapAdapter
import com.jeff.movieviewer.android.base.extension.shortToast
import com.jeff.movieviewer.database.local.SeatMap
import com.jeff.movieviewer.databinding.ActivitySeatMapBinding
import com.jeff.movieviewer.main.seatmap.presenter.SeatMapPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.content_seat_map.view.*
import javax.inject.Inject


class SeatMapActivity : MvpActivity<SeatMapView, SeatMapPresenter>(),
    SeatMapView {

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

    @Inject
    internal lateinit var seatMapPresenter: SeatMapPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_map)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_seat_map)

        seatMapPresenter.loadSeatMap()

        val dates = listOf("Apr 05, Wed", "Apr 08, Thu", "Apr 15, Fri")
        // access the spinner
        val spinner = binding.root.dates
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dates)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    shortToast("Selected Date is ${dates[position]}")
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        seatMapPresenter.loadSchedule()

        binding.root.clear_selected_seats.setOnClickListener {
            adapter.clearSelectedSeats()
            clearSelectedSeats()
        }

    }

    override fun createPresenter(): SeatMapPresenter {
        return seatMapPresenter
    }

    override fun generateSeatMap(seatMap: SeatMap) {
        val numberOfColumns = 36
        val compiledSeatMap = mutableListOf<String>()
        for(s in seatMap.seatmap) {
            for (x in s.reversed()) {
                compiledSeatMap.add(x)
            }
        }

        binding.root.seatMapRecyclerView.layoutManager = GridLayoutManager(this, numberOfColumns)
        adapter = SeatMapAdapter(
            this,
            compiledSeatMap,
            seatMap.availableSeats.available)
        binding.root.seatMapRecyclerView.adapter = adapter
    }

    fun populateSelectedSeats(selectedSeats: MutableList<String>) {


        if (selectedSeats.isEmpty()) {
            clearSelectedSeats()
        } else {
            binding.root.selected_seats_layout.removeAllViews()
            for (s in selectedSeats) {
                addTextViewToLayout(s)
            }
        }
    }

    private fun clearSelectedSeats() {
        binding.root.selected_seats_layout.removeAllViews()
        addTextViewToLayout("")
    }

    private fun addTextViewToLayout(s: String) {
        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(8, 8, 8, 8)
        val selectedSeat = TextView(this)
        selectedSeat.textSize = 20f
        selectedSeat.text = s
        selectedSeat.background = getDrawable(R.color.red)
        selectedSeat.layoutParams = params

        // add TextView to LinearLayout
        binding.root.selected_seats_layout.addView(selectedSeat)
    }
}