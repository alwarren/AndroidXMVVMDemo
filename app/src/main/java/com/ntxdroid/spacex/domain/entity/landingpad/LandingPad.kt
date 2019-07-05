package com.ntxdroid.spacex.domain.entity.landingpad

import com.google.gson.annotations.SerializedName

// verified classes and types 1/22/2019
/**
 * https://api.spacexdata.com/v3/landpads
 * https://api.spacexdata.com/v3/landpads/{{id}}
 */
data class LandingPad(
    val id: String,
    @SerializedName("full_name")
    val fullName: String,
    val status: String,
    val location: Location,
    @SerializedName("landing_type")
    val landingType: String,
    @SerializedName("attempted_landings")
    val attemptedLandings: Int,
    @SerializedName("successful_landings")
    val successfulLandings: Int,
    val wikipedia: String,
    val details: String
)