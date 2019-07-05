package com.ntxdroid.spacex.domain.entity.launch

import com.google.gson.annotations.SerializedName

data class Telemetry(
    @SerializedName("flight_club")
    val flightClub: String?
)