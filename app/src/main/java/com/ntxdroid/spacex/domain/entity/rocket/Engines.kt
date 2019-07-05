package com.ntxdroid.spacex.domain.entity.rocket

import com.google.gson.annotations.SerializedName

data class Engines(
    val number: Int,
    val type: String,
    val version: String,
    val layout: Any?,
    @SerializedName("engine_loss_max")
    val engineLossMax: Any?,
    @SerializedName("propellant_1")
    val propellant1: String,
    @SerializedName("propellant_2")
    val propellant2: String,
    @SerializedName("thrust_sea_level")
    val thrustSeaLevel: ThrustSeaLevel,
    @SerializedName("thrust_vacuum")
    val thrustVacuum: ThrustVacuum,
    @SerializedName("thrust_to_weight")
    val thrustToWeight: Any?
)