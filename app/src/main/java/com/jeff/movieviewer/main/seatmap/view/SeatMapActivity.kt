package com.jeff.movieviewer.main.seatmap.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.jeff.movieviewer.R
import com.jeff.movieviewer.adapter.SeatMapAdapter
import com.jeff.movieviewer.android.base.extension.hide
import com.jeff.movieviewer.android.base.extension.show
import com.jeff.movieviewer.database.local.Schedule
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
        private var EXTRA_THEATRE = "EXTRA_THEATRE"

        fun getStartIntent(
            context: Context,
            id: Int,
            theatre: String


        ): Intent {
            return Intent(context, SeatMapActivity::class.java)
                .putExtra(EXTRA_ID, id)
                .putExtra(EXTRA_THEATRE, theatre)
        }
    }
    private var selectedSchedulePrice: Int? = 0
    private var seatMap: SeatMap? = null

    private lateinit var binding : ActivitySeatMapBinding

    private lateinit var adapter: SeatMapAdapter

    @Inject
    internal lateinit var seatMapPresenter: SeatMapPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_map)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_seat_map)
        setupToolbar()

        seatMapPresenter.loadSeatMap()
        seatMapPresenter.loadSchedule()

        binding.root.clear_selected_seats.setOnClickListener {
            adapter.clearSelectedSeats()
            clearSelectedSeats()
        }

        binding.root.theatre.text = String.format("Theatre : ${getTheatre()}")

    }
    private var isFullScreened = false
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.fullscreen_enter ->         //add the function to perform here
            {
                binding.root.header_layout.hide()
                binding.root.selected_seats_layout.hide()
                binding.root.bottom_card_view.hide()
                //binding.root.zoom_layout.setAlignment(Alignment.CENTER)
                //binding.root.zoom_layout.rotation = 90f
                isFullScreened = true
                invalidateOptionsMenu()

            }
            R.id.fullscreen_exit ->         //add the function to perform here
            {
                binding.root.header_layout.show()
                binding.root.selected_seats_layout.show()
                binding.root.bottom_card_view.show()
                //binding.root.zoom_layout.rotation = 0f
                //binding.root.zoom_layout.setAlignment(Alignment.TOP)


                isFullScreened = false
                invalidateOptionsMenu()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        val fullScreenExitItem = menu!!.findItem(R.id.fullscreen_exit)
        val fullscreenEnterItem = menu.findItem(R.id.fullscreen_enter)
        if (isFullScreened) {
            fullscreenEnterItem.isVisible = false
            fullScreenExitItem.isVisible = true
        } else {
            fullscreenEnterItem.isVisible = true
            fullScreenExitItem.isVisible = false
        }
        return true
    }

    private fun getTheatre(): String? = intent.getStringExtra(EXTRA_THEATRE)

    override fun createPresenter(): SeatMapPresenter {
        return seatMapPresenter
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)

        supportActionBar!!.title = ""
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun setSchedule(schedule: Schedule) {
        setDate(schedule)
        setCinemas(schedule)
        setTimes(schedule)
    }

    private fun setDate(schedule: Schedule) {
        val dateLabels: MutableList<String> = mutableListOf()
        for (d in schedule.dates) {
            dateLabels.add(d.label)
        }
        val datesSpinner = binding.root.dates
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dateLabels)
        datesSpinner.adapter = spinnerAdapter
        datesSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun setCinemas(schedule: Schedule) {
        val cinemaLabels: MutableList<String> = mutableListOf()
        for (c in schedule.cinemas) {
            for (cc in c.cinemas) {
                cinemaLabels.add(cc.label)
            }
        }
        val cinemasSpinner = binding.root.cinemas
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cinemaLabels)
        cinemasSpinner.adapter = adapter
        cinemasSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun setTimes(schedule: Schedule) {
        val timeLabels: MutableList<String> = mutableListOf()
        for (t in schedule.times[0].times) {
            timeLabels.add(t.label)
        }
        val timesSpinner = binding.root.times
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, timeLabels)
        timesSpinner.adapter = spinnerAdapter
        timesSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                val price = schedule.times[0].times[position].price
                selectedSchedulePrice = price.toInt()
                setTotalPrice()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun setTotalPrice() {
        binding.content.total.text = String.format("Total : ${adapter.getSelectedSeats()!!.size * selectedSchedulePrice!!}")
    }
    override fun generateSeatMap(seatMap: SeatMap) {
        this.seatMap = seatMap
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
            seatMap.availableSeats.available
        )
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
        setTotalPrice()
    }

    private fun clearSelectedSeats() {
        binding.root.selected_seats_layout.removeAllViews()
        addTextViewToLayout("")
        setTotalPrice()
        generateSeatMap(this.seatMap!!)
    }

    private fun addTextViewToLayout(s: String) {
        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        params.setMargins(8, 8, 8, 8)
        val selectedSeat = TextView(this)
        selectedSeat.textSize = 20f
        selectedSeat.text = s
        selectedSeat.background = getDrawable(R.color.red)
        selectedSeat.setTextColor(resources.getColor(R.color.white))
        selectedSeat.layoutParams = params

        // add TextView to LinearLayout
        binding.root.selected_seats_layout.addView(selectedSeat)
    }

    override fun showMessage(message: String) {
        Snackbar.make(
            binding.coordLayout,
            message,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun showProgressBar() {
        binding.content.progressbar.show()
    }
    override fun hideProgressBar() {
        binding.content.progressbar.hide()
    }
}

