package com.shivansh.officetask.tmdbReponse.personResponse

import com.google.gson.annotations.SerializedName

data class PersonResponse(
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val results: List<PersonResults>,
    @SerializedName("total_pages")
    val totalPages : Int,
    @SerializedName("total_results")
    val totalResults : Int
)
