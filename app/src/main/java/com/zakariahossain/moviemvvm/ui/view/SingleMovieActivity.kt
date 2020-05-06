package com.zakariahossain.moviemvvm.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.AppBarLayout
import com.zakariahossain.moviemvvm.R
import com.zakariahossain.moviemvvm.data.api.MovieDbClient
import com.zakariahossain.moviemvvm.data.models.MovieDetails
import com.zakariahossain.moviemvvm.data.repository.MovieDetailsRepository
import com.zakariahossain.moviemvvm.ui.viewmodels.SingleMovieViewModel
import com.zakariahossain.moviemvvm.ui.viewmodels.ViewModelProviderFactory
import com.zakariahossain.moviemvvm.util.Constants.MOVIE_ID
import com.zakariahossain.moviemvvm.util.Constants.POSTER_BASE_URL
import com.zakariahossain.moviemvvm.util.Constants.VIDEO_NAME
import com.zakariahossain.moviemvvm.util.Constants.VIDEO_PATH
import com.zakariahossain.moviemvvm.util.toastShort
import kotlinx.android.synthetic.main.activity_single_movie.*
import kotlin.math.abs

class SingleMovieActivity : AppCompatActivity() {

    private lateinit var movieVideoPath: String
    private lateinit var movieVideoName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)

        val movieId: Int = intent.getIntExtra(MOVIE_ID, 1)
        val movieDetailsRepository = MovieDetailsRepository(MovieDbClient.getApiService())
        val viewModelProviderFactory = ViewModelProviderFactory(SingleMovieViewModel(movieDetailsRepository))
        val singleMovieViewModel = ViewModelProvider(this, viewModelProviderFactory)[SingleMovieViewModel::class.java]

        singleMovieViewModel.networkState.observe(this, Observer {
            toastShort(it.message)
        })

        singleMovieViewModel.getSingleMovieDetails(movieId)
            .observe(this, Observer { bindUI(it) })

        singleMovieViewModel.getSingleMovieVideos(movieId)
            .observe(this, Observer {
                val result = it.results.first()
                movieVideoPath = result.key
                movieVideoName = result.name
            })

        fab.setOnClickListener {
            val intent = Intent(this, YoutubeActivity::class.java)
            intent.putExtra(VIDEO_NAME, movieVideoName)
            intent.putExtra(VIDEO_PATH, movieVideoPath)
            startActivity(intent)
        }
    }

    private fun bindUI(it: MovieDetails) {
        Glide.with(this)
            .load(POSTER_BASE_URL+it.posterPth)
            .apply( RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
            .into(movieImageView)

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if(abs(verticalOffset) == appBarLayout.totalScrollRange) { //collapse
                titleCollapse.title = it.title
            } else {
                titleCollapse.title = ""
            }
        })
    }
}
