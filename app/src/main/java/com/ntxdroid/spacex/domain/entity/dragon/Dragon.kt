package com.ntxdroid.spacex.domain.entity.dragon

import com.google.gson.annotations.SerializedName

// verified classes and types 1/22/2019
/**
 * https://api.spacexdata.com/v3/dragons
 * https://api.spacexdata.com/v3/dragons/{{id}}
 */
data class Dragon(
    val id: String,
    val name: String,
    val type: String,
    val active: Boolean,
    @SerializedName("crew_capacity")
    val crewCapacity: Int,
    @SerializedName("sidewall_angle_deg")
    val sidewallAngleDeg: Int,
    @SerializedName("orbit_duration_yr")
    val orbitDurationYr: Int,
    @SerializedName("dry_mass_kg")
    val dryMassKg: Int,
    @SerializedName("dry_mass_lb")
    val dryMassLb: Int,
    @SerializedName("first_flight")
    val firstFlight: String,
    @SerializedName("heat_shield")
    val heatShield: HeatShield,
    val thrusters: List<Thruster>,
    @SerializedName("launch_payload_mass")
    val launchPayloadMass: LaunchPayloadMass,
    @SerializedName("launch_payload_vol")
    val launchPayloadVol: LaunchPayloadVol,
    @SerializedName("return_payload_mass")
    val returnPayloadMass: ReturnPayloadMass,
    @SerializedName("return_payload_vol")
    val returnPayloadVol: ReturnPayloadVol,
    @SerializedName("pressurized_capsule")
    val pressurizedCapsule: PressurizedCapsule,
    val trunk: Trunk,
    @SerializedName("height_w_trunk")
    val heightWTrunk: HeightWTrunk,
    val diameter: Diameter,
    @SerializedName("flickr_images")
    val flickrImages: List<String>,
    val wikipedia: String,
    val description: String
)