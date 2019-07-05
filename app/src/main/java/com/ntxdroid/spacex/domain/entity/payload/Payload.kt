package com.ntxdroid.spacex.domain.entity.payload

import com.google.gson.annotations.SerializedName

// verified classes and types 1/22/2019
/**
 * https://api.spacexdata.com/v3/payloads
 * https://api.spacexdata.com/v3/payloads/{{payload_id}}
 */
data class Payload(
    @SerializedName("payload_id")
    val payloadId: String,
    @SerializedName("norad_id")
    val noradId: List<Any>,
    val reused: Boolean,
    val customers: List<String>,
    val nationality: String,
    val manufacturer: String,
    @SerializedName("payload_type")
    val payloadType: String,
    @SerializedName("payload_mass_kg")
    val payloadMassKg: Double?,
    @SerializedName("payload_mass_lbs")
    val payloadMassLbs: Double?,
    val orbit: String,
    @SerializedName("orbit_params")
    val orbitParams: OrbitParams,
    @SerializedName("cap_serial")
    val capSerial: String?,
    @SerializedName("mass_returned_kg")
    val massReturnedKg: Double?,
    @SerializedName("mass_returned_lbs")
    val massReturnedLbs: Double?,
    @SerializedName("flight_time_sec")
    val flightTimeSec: Int?,
    @SerializedName("cargo_manifest")
    val cargoManifest: String?
)