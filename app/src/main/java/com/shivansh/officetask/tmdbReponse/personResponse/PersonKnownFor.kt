package com.shivansh.officetask.tmdbReponse.personResponse

import com.google.gson.annotations.SerializedName

data class PersonKnownFor(
    @SerializedName("adult")
    val adult : Boolean,
    @SerializedName("backdrop_path")
    val backDropPath : String,
    @SerializedName("genre_ids")
    val genreId : List<Int>,
    @SerializedName("id")
    val id : Int,
    @SerializedName("media_type")
    val mediaType : String,
    @SerializedName("original_language")
    val originalLanguage : String,
    @SerializedName("original_title")
    val originalTitle : String,
    @SerializedName("overview")
    val overView : String,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("release_date")
    val releaseDate : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("video")
    val video : Boolean,
    @SerializedName("vote_average")
    val voteAverage : Double,
    @SerializedName("vote_count")
    val voteCount : Int
)
