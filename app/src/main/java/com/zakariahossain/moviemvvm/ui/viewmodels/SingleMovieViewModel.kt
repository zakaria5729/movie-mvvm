package com.zakariahossain.moviemvvm.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zakariahossain.moviemvvm.data.models.MovieDetails
import com.zakariahossain.moviemvvm.data.repository.MovieDetailsRepository
import com.zakariahossain.moviemvvm.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel(private val movieDetailsRepository: MovieDetailsRepository): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    init {
        movieDetailsRepository(compositeDisposable)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieDetailsRepository.getMovieDetailsNetworkState()
    }

    fun getSingleMovieDetails(movieId: Int): LiveData<MovieDetails> {
        return movieDetailsRepository.getSingleMovieDetails(movieId);
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}