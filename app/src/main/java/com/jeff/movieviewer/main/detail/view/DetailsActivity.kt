package com.jeff.movieviewer.main.detail.view

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.jeff.movieviewer.R
import com.jeff.movieviewer.android.base.extension.*
import com.jeff.movieviewer.database.local.Movie
import com.jeff.movieviewer.databinding.ActivityDetailsBinding
import com.jeff.movieviewer.main.detail.presenter.DetailsPresenter
import com.jeff.movieviewer.main.seatmap.view.SeatMapActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.content_details.view.*
import javax.inject.Inject
import kotlin.math.roundToInt


class DetailsActivity : MvpActivity<DetailsView, DetailsPresenter>(),
    DetailsView {

    companion object {
        private var EXTRA_ID = "EXTRA_ID"
        private var EXTRA_TITLE = "EXTRA_TITLE"
        private var EXTRA_URL = "EXTRA_URL"
        private var EXTRA_THUMBNAIL_URL = "EXTRA_THUMBNAIL_URL"

        fun getStartIntent(
            context: Context,
            id : Int,
            title : String,
            url : String,
            thumbnailUrl : String


        ): Intent {
            return Intent(context, DetailsActivity::class.java)
                .putExtra(EXTRA_ID, id)
                .putExtra(EXTRA_TITLE, title)
                .putExtra(EXTRA_URL, url)
                .putExtra(EXTRA_THUMBNAIL_URL, thumbnailUrl)
        }
    }

    @Inject
    internal lateinit var detailsPresenter : DetailsPresenter

    private lateinit var binding : ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(findViewById(R.id.toolbar))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        setupToolbar()
        detailsPresenter.loadMovie()

        //setDetails(getUrl()!!, getTittle()!!)
        //userDetailsPresenter.loadUserDetails(getUserName()!!, getId()!!)
        //userDetailsPresenter.loadNotes(getId()!!)
        /*binding.root.save_notes.setOnClickListener {
            userDetailsPresenter.updateNotes(
                binding.root.notes.text.toString(),
                getId()!!
            )
        }*/
    }

    private fun getUrl(): String? = intent.getStringExtra(EXTRA_URL)
    private fun getThumnailUrl(): String? = intent.getStringExtra(EXTRA_THUMBNAIL_URL)
    private fun getTittle(): String? = intent.getStringExtra(EXTRA_TITLE)
    private fun getId(): Int? = intent.getIntExtra(EXTRA_ID, -1)

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)

        supportActionBar!!.title = ""
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun createPresenter(): DetailsPresenter {
        return detailsPresenter
    }

    /*override fun setNotes(notes: Notes) {
        binding.root.notes.setText(notes.content)
    }*/

    override fun showMessage(message: String) {
        Snackbar.make(binding.coordLayout,
            message,
            Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun setMovieDetails(movie: Movie) {
        movie.let {

            binding.root.title.text = it.canonicalTitle
            binding.root.genre.text = it.genre
            binding.root.advisory_rating.text = String.format("Rated ${it.advisoryRating}")
            binding.root.synopsis.text = it.synopsis


            val cast = it.cast.joinToString { cast -> String.format(cast) }
            binding.root.cast.text = String.substringWithDots( cast , 80)
            binding.root.release_date.text = dateFormatToMMddyyyy(it.releaseDate)
            val parsedFloat = it.runtimeMins.toFloatOrNull()
            binding.root.duration.text = minuteToHHMM(parsedFloat!!.roundToInt())

            Glide
                .with(this)
                .load(it.posterLandscape)
                .centerCrop()
                .placeholder(ColorDrawable(resources.getColor(R.color.colorPrimary)))
                .into(binding.posterLandscape)

            Glide
                .with(this)
                .load(it.poster)
                .placeholder(ColorDrawable(resources.getColor(R.color.colorPrimary)))
                .into(binding.root.poster)

            binding.viewSeatMap.setOnClickListener { movie ->
                val intent = SeatMapActivity.getStartIntent(this, movie.id)
                this.startActivity(intent)
            }
        }
    }

    override fun startShimmerAnimations() {
        binding.root.shimmer_details_container.startShimmerAnimation()
        binding.root.shimmer_follows_container.startShimmerAnimation()
    }

    override fun hideShimmerPlaceholders() {
        binding.root.title_shimmer.hide()
        binding.root.advisory_rating_shimmer.hide()
        binding.root.cast_shimmer.hide()
        binding.root.genre_shimmer.hide()
        binding.root.synopsis_shimmer.hide()
        binding.root.synopsis2_shimmer.hide()
        /*
        binding.root.genre_shimmer.hide()
        binding.root.advisory_rating_shimmer.hide()
        binding.root.synopsis_shimmer.hide()
        binding.root.cast_shimmer.hide()*/
        binding.root.release_date_shimmer.hide()
        binding.root.duration_shimmer.hide()
    }

    override fun stopShimmerAnimations() {
        binding.root.shimmer_details_container.stopShimmerAnimation();
        binding.root.shimmer_follows_container.stopShimmerAnimation();
    }
}