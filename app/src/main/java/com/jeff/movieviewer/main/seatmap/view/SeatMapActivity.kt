package com.jeff.movieviewer.main.seatmap.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.jeff.movieviewer.R
import com.jeff.movieviewer.adapter.SeatMapAdapter
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
    }

    override fun createPresenter(): SeatMapPresenter {
        return seatMapPresenter
    }

    override fun generateSeatMap(seatMap: SeatMap) {
        val numberOfColumns = 36
        val compiledSeatMap = mutableListOf<String>()
        for(s in seatMap.seatmap) {
            for (x in s) {
                compiledSeatMap.add(x)
            }
        }

        binding.root.seatMapRecyclerView.layoutManager = GridLayoutManager(this, numberOfColumns)
        adapter = SeatMapAdapter(this, compiledSeatMap, seatMap.availableSeats.available)
        binding.root.seatMapRecyclerView.adapter = adapter
    }
}