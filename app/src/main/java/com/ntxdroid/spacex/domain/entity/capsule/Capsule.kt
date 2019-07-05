package com.ntxdroid.spacex.domain.entity.capsule

import com.google.gson.annotations.SerializedName

// verified classes and types 1/22/2019
/**
 * https://api.spacexdata.com/v3/capsules
 * https://api.spacexdata.com/v3/capsules/{{capsule_serial}}
 */

data class Capsule(
    @SerializedName("capsule_serial")
    val capsuleSerial: String,
    @SerializedName("capsule_id")
    val capsuleId: String,
    val status: String,
    @SerializedName("original_launch")
    val originalLaunch: String?,
    @SerializedName("original_launch_unix")
    val originalLaunchUnix: Int?,
    val missions: List<Mission>,
    val landings: Int,
    val type: String,
    val details: String?,
    @SerializedName("reuse_count")
    val reuseCount: Int
)