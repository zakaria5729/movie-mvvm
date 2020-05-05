package com.zakariahossain.moviemvvm.data.models

import com.google.gson.annotations.SerializedName

data class MovieDetails (
	val id : Int,
	val budget : Int,
	val overview : String,
	val popularity : Double,
	val revenue : Int,
	val runtime : Int,
	val status : String,
	val tagline : String,
	val title : String,
	val video : Boolean,
	val genres : List<Genres>,

	@SerializedName("vote_average")
	val rating : Double,

	@SerializedName("poster_path")
	val posterPth : String,

	@SerializedName("release_date")
	val releaseDate : String
)