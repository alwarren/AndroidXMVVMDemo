package com.ntxdroid.spacex.domain.entity.dragon

import com.google.gson.annotations.SerializedName

data class LaunchPayloadVol(
    @SerializedName("cubic_meters")
    val cubicMeters: Int,
    @SerializedName("cubic_feet")
    val cubicFeet: Int
)