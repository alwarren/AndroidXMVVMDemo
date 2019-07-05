package com.ntxdroid.spacex.domain.entity.dragon

import com.google.gson.annotations.SerializedName

data class Trunk(
    @SerializedName("trunk_volume")
    val trunkVolume: TrunkVolume,
    val cargo: Cargo
)