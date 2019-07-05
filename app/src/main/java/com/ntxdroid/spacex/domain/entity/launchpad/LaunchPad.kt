package com.ntxdroid.spacex.domain.entity.launchpad

import com.google.gson.annotations.SerializedName

// verified classes and types 1/22/2019
/**
 * https://api.spacexdata.com/v3/cores/past
 * https://api.spacexdata.com/v3/launchpads/{{site_id}}
 */
data class LaunchPad(
    val id: Int,
    val status: String,
    val location: Location,
    @SerializedName("vehicles_launched")
    val vehiclesLaunched: List<String>,
    @SerializedName("attempted_launches")
    val attemptedLaunches: Int,
    @SerializedName("successful_launches")
    val successfulLaunches: Int,
    val wikipedia: String,
    val details: String,
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("site_name_long")
    val siteNameLong: String
)