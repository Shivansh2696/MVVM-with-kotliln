package com.shivansh.officetask.tmdbReponse.personResponse

import com.google.gson.annotations.SerializedName

data class PersonResults(
    @SerializedName("adult")
    val adult : Boolean,
    @SerializedName("gender")
    val gender : Int,
    @SerializedName("id")
    val id : Int,
    @SerializedName("known_for")
    val knownFor : List<PersonKnownFor>,
    @SerializedName("known_for_department")
    val KnowForDepartment : String,
    @SerializedName("name")
    val personName : String,
    @SerializedName("popularity")
    val popularity : Double,
    @SerializedName("profile_path")
    val profilePhoto : String
)
