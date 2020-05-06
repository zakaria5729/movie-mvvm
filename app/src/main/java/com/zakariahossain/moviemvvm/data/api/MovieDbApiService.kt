package com.zakariahossain.moviemvvm.data.api

import com.zakariahossain.moviemvvm.data.models.MovieDetails
import com.zakariahossain.moviemvvm.data.models.MovieVideos
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDbApiService {

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(@Path("movie_id") id: Int): Single<MovieVideos>
}