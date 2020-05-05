package com.zakariahossain.moviemvvm.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zakariahossain.moviemvvm.data.api.MovieDbApiService
import com.zakariahossain.moviemvvm.data.models.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsDataSource(private val apiService: MovieDbApiService, private val compositeDisposable: CompositeDisposable) {
    private val networkStateMutableLiveData = MutableLiveData<NetworkState>()
    val networkStateLiveData: LiveData<NetworkState> = networkStateMutableLiveData

    private val movieDetailsResponseMutableLiveData = MutableLiveData<MovieDetails>()
    val movieDetailsResponseLiveData: LiveData<MovieDetails> = movieDetailsResponseMutableLiveData

    fun fetchMovieDetails(movieId: Int) {
        networkStateMutableLiveData.value = NetworkState.LOADING

        try {
            compositeDisposable.add(
                apiService.getMovieDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        networkStateMutableLiveData.postValue(NetworkState.LOADED)
                        movieDetailsResponseMutableLiveData.postValue(it)
                    }, {
                        networkStateMutableLiveData.postValue(NetworkState.ERROR)
                        Log.e("MovieDetailsDataSource", it.message ?: "unknown error")
                    })
            )
        } catch (e: Exception) {
            Log.e("MovieDetailsDataSource", e.message ?: "unknown error")
        }
    }
}