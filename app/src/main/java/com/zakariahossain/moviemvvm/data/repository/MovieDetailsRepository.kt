package com.zakariahossain.moviemvvm.data.repository

import androidx.lifecycle.LiveData
import com.zakariahossain.moviemvvm.data.api.MovieDbApiService
import com.zakariahossain.moviemvvm.data.models.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiService: MovieDbApiService) {
    private lateinit var movieDetailsDataSource: MovieDetailsDataSource

    operator fun invoke(compositeDisposable: CompositeDisposable) {
        movieDetailsDataSource = MovieDetailsDataSource(apiService, compositeDisposable)
    }

    fun getSingleMovieDetails(movieId: Int): LiveData<MovieDetails> {
        movieDetailsDataSource.fetchMovieDetails(movieId)
        return movieDetailsDataSource.movieDetailsResponseLiveData
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsDataSource.networkStateLiveData
    }
}
