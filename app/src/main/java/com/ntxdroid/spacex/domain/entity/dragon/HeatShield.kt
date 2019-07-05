package com.ntxdroid.spacex.domain.entity.dragon

import com.google.gson.annotations.SerializedName

data class HeatShield(
    val material: String,
    @SerializedName("size_meters")
    val sizeMeters: Double,
    @SerializedName("temp_degrees")
    val tempDegrees: Int,
    @SerializedName("dev_partner")
    val devPartner: String
)