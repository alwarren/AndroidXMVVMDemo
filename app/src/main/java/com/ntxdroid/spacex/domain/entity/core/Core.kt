package com.ntxdroid.spacex.domain.entity.core

import com.google.gson.annotations.SerializedName

// verified classes and types 1/22/2019
/**
 * https://api.spacexdata.com/v3/cores
 * https://api.spacexdata.com/v3/cores/{{core_serial}}
 * https://api.spacexdata.com/v3/cores/upcoming
 * https://api.spacexdata.com/v3/cores/past
 */
data class Core(
    @SerializedName("core_serial")
    val coreSerial: String,
    val block: Int?,
    val status: String,
    @SerializedName("original_launch")
    val originalLaunch: String?,
    @SerializedName("original_launch_unix")
    val originalLaunchUnix: Int?,
    val missions: List<Mission>,
    @SerializedName("reuse_count")
    val reuseCount: Int,
    @SerializedName("rtls_attempts")
    val rtlsAttempts: Int,
    @SerializedName("rtls_landings")
    val rtlsLandings: Int,
    @SerializedName("asds_attempts")
    val asdsAttempts: Int,
    @SerializedName("asds_landings")
    val asdsLandings: Int,
    @SerializedName("water_landing")
    val waterLanding: Boolean,
    val details: String?
)