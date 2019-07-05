package com.ntxdroid.spacex.domain.entity.rocket

import com.google.gson.annotations.SerializedName

data class SecondStage(
    val reusable: Boolean,
    val engines: Int,
    @SerializedName("fuel_amount_tons")
    val fuelAmountTons: Double,
    @SerializedName("burn_time_sec")
    val burnTimeSec: Int?,
    val thrust: Thrust,
    val payloads: Payloads
)