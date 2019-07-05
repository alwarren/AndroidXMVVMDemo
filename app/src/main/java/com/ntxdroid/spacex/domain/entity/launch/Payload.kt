package com.ntxdroid.spacex.domain.entity.launch

import com.google.gson.annotations.SerializedName

data class Payload(
    @SerializedName("payload_id")
    val payloadId: String,
    @SerializedName("norad_id")
    val noradId: List<Int>,
    val reused: Boolean,
    val customers: List<String>,
    val nationality: String,
    val manufacturer: String?,
    @SerializedName("payload_type")
    val payloadType: String,
    @SerializedName("payload_mass_kg")
    val payloadMassKg: Double?,
    @SerializedName("payload_mass_lbs")
    val payloadMassLbs: Double?,
    val orbit: String,
    @SerializedName("orbit_params")
    val orbitParams: OrbitParams
)