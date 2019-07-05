package com.ntxdroid.spacex.domain.entity.dragon

import com.google.gson.annotations.SerializedName

data class Thruster(
    val type: String,
    val amount: Int,
    val pods: Int,
    @SerializedName("fuel_1")
    val fuel1: String,
    @SerializedName("fuel_2")
    val fuel2: String,
    val thrust: Thrust
)