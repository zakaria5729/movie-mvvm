package com.zakariahossain.moviemvvm.data.api

import com.zakariahossain.moviemvvm.data.models.MovieDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDbApiService {

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>
}