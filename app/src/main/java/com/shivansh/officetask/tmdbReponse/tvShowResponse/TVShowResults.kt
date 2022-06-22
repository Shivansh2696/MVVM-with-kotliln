package com.shivansh.officetask.tmdbReponse.tvShowResponse

import com.google.gson.annotations.SerializedName

data class TVShowResults(
    @SerializedName("backdrop_path")
    val backgroundImage : String,
    @SerializedName("first_air_date")
    val firstAirDate : String,
    @SerializedName("genre_ids")
    val genreId : List<Int>,
    @SerializedName("id")
    val Id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("origin_country")
    val originCountry : List<String>,
    @SerializedName("original_language")
    val originalLanguage : String,
    @SerializedName("original_name")
    val originalName : String,
    @SerializedName("overview")
    val overView : String,
    @SerializedName("popularity")
    val popularity : Double,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("vote_average")
    val voteAverage : Double,
    @SerializedName("vote_count")
    val voteCount : Int
)
