package com.ntxdroid.spacex.domain.entity.dragon

import com.google.gson.annotations.SerializedName

data class PressurizedCapsule(
    @SerializedName("payload_volume")
    val payloadVolume: PayloadVolume
)