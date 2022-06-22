package com.shivansh.officetask.tmdbReponse.tvShowResponse

import com.google.gson.annotations.SerializedName
import com.sunny.kotlinpractice.tmdbResponse.moviesResponse.MovieResults

data class TVShowResponse(
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val results: List<TVShowResults>,
    @SerializedName("total_pages")
    val totalPages : Int,
    @SerializedName("total_results")
    val totalResults : Int
)
