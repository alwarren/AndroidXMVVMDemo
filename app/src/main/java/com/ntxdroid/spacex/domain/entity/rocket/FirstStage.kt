package com.ntxdroid.spacex.domain.entity.rocket

import com.google.gson.annotations.SerializedName

data class FirstStage(
    val reusable: Boolean,
    val engines: Int,
    @SerializedName("fuel_amount_tons")
    val fuelAmountTons: Double,
    @SerializedName("burn_time_sec")
    val burnTimeSec: Int?,
    @SerializedName("thrust_sea_level")
    val thrustSeaLevel: ThrustSeaLevel,
    @SerializedName("thrust_vacuum")
    val thrustVacuum: ThrustVacuum
)