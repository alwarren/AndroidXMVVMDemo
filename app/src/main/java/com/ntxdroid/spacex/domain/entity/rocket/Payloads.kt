package com.ntxdroid.spacex.domain.entity.rocket

import com.google.gson.annotations.SerializedName

data class Payloads(
    @SerializedName("option_1")
    val option1: String,
    @SerializedName("option_2")
    val option2: String,
    @SerializedName("composite_fairing")
    val compositeFairing: CompositeFairing
)