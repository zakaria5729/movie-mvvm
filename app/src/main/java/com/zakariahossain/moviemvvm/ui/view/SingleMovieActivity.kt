package com.zakariahossain.moviemvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zakariahossain.moviemvvm.R
import com.zakariahossain.moviemvvm.data.api.MovieDbClient
import com.zakariahossain.moviemvvm.data.models.MovieDetails
import com.zakariahossain.moviemvvm.data.repository.MovieDetailsRepository
import com.zakariahossain.moviemvvm.ui.viewmodels.ViewModelProviderFactory
import com.zakariahossain.moviemvvm.ui.viewmodels.SingleMovieViewModel

class SingleMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)

        val movieDetailsRepository = MovieDetailsRepository(MovieDbClient.getApiService())
        val viewModelProviderFactory = ViewModelProviderFactory(SingleMovieViewModel(movieDetailsRepository))
        val singleMovieViewModel = ViewModelProvider(this, viewModelProviderFactory)[SingleMovieViewModel::class.java]

        singleMovieViewModel.networkState.observe(this, Observer {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        })

        singleMovieViewModel.getSingleMovieDetails(intent.getIntExtra("movie_id", 1)).observe(this, Observer {
            bindUI(it)
        })
    }

    private fun bindUI(it: MovieDetails) {
        Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
    }
}
