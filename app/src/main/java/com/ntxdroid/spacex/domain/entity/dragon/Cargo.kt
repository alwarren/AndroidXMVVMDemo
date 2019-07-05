package com.ntxdroid.spacex.domain.entity.dragon

import com.google.gson.annotations.SerializedName

data class Cargo(
    @SerializedName("solar_array")
    val solarArray: Int,
    @SerializedName("unpressurized_cargo")
    val unpressurizedCargo: Boolean
)