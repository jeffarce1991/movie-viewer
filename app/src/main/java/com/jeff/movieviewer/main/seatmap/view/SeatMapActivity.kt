package com.jeff.movieviewer.main.seatmap.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jeff.movieviewer.R
import com.jeff.movieviewer.main.detail.view.DetailsActivity

class SeatMapActivity : AppCompatActivity() {

    companion object {
        private var EXTRA_ID = "EXTRA_ID"

        fun getStartIntent(
            context: Context,
            id : Int


        ): Intent {
            return Intent(context, DetailsActivity::class.java)
                .putExtra(EXTRA_ID, id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_map)
    }
}