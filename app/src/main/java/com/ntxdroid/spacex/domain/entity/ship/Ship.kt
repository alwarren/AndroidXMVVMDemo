package com.ntxdroid.spacex.domain.entity.ship

import com.google.gson.annotations.SerializedName

// verified classes and types 1/22/2019
/**
 * https://api.spacexdata.com/v3/ships
 * https://api.spacexdata.com/v3/ships/{{ship_id}}
 */
data class Ship(
    @SerializedName("ship_id")
    val shipId: String,
    @SerializedName("ship_name")
    val shipName: String,
    @SerializedName("ship_model")
    val shipModel: String?,
    @SerializedName("ship_type")
    val shipType: String,
    val roles: List<String>,
    val active: Boolean,
    val imo: Int?,
    val mmsi: Int?,
    val abs: Int?,
    @SerializedName("class")
    val clazz: Int?,
    @SerializedName("weight_lbs")
    val weightLbs: Int?,
    @SerializedName("weight_kg")
    val weightKg: Int?,
    @SerializedName("year_built")
    val yearBuilt: Int?,
    @SerializedName("home_port")
    val homePort: String,
    val status: String?,
    @SerializedName("speed_kn")
    val speedKn: Double?,
    @SerializedName("course_deg")
    val courseDeg: Int?,
    val position: Position,
    @SerializedName("successful_landings")
    val successfulLandings: Int?,
    @SerializedName("attempted_landings")
    val attemptedLandings: Int?,
    val missions: List<Mission>,
    val url: String?,
    val image: String?,
    @SerializedName("attempted_catches")
    val attemptedCatches: Int,
    @SerializedName("successful_catches")
    val successfulCatches: Int
)