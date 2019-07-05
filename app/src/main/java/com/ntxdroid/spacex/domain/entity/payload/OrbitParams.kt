package com.ntxdroid.spacex.domain.entity.payload

import com.google.gson.annotations.SerializedName

data class OrbitParams(
    @SerializedName("reference_system")
    val referenceSystem: String?,
    val regime: String?,
    val longitude: Double?,
    @SerializedName("semi_major_axis_km")
    val semiMajorAxisKm: Double?,
    val eccentricity: Double?,
    @SerializedName("periapsis_km")
    val periapsisKm: Double?,
    @SerializedName("apoapsis_km")
    val apoapsisKm: Double?,
    @SerializedName("inclination_deg")
    val inclinationDeg: Double?,
    @SerializedName("period_min")
    val periodMin: Double?,
    @SerializedName("lifespan_years")
    val lifespanYears: Int?,
    val epoch: String?,
    @SerializedName("mean_motion")
    val meanMotion: Double?,
    val raan: Double?,
    @SerializedName("arg_of_pericenter")
    val argOfPericenter: Double?,
    @SerializedName("mean_anomaly")
    val meanAnomaly: Double?
)