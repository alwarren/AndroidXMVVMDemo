package com.ntxdroid.spacex.domain.entity.rocket

import com.google.gson.annotations.SerializedName

// verified classes and types 1/22/2019
/**
 * https://api.spacexdata.com/v3/rockets
 * https://api.spacexdata.com/v3/rockets/{{rocket_id}}
 */
data class Rocket(
    val id: Int,
    val active: Boolean,
    val stages: Int,
    val boosters: Int,
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int,
    @SerializedName("success_rate_pct")
    val successRatePct: Int,
    @SerializedName("first_flight")
    val firstFlight: String,
    val country: String,
    val company: String,
    val height: Height,
    val diameter: Diameter,
    val mass: Mass,
    @SerializedName("payload_weights")
    val payloadWeights: List<PayloadWeight>,
    @SerializedName("first_stage")
    val firstStage: FirstStage,
    @SerializedName("second_stage")
    val secondStage: SecondStage,
    val engines: Engines,
    @SerializedName("landing_legs")
    val landingLegs: LandingLegs,
    @SerializedName("flickr_images")
    val flickrImages: List<String>,
    val wikipedia: String,
    val description: String,
    @SerializedName("rocket_id")
    val rocketId: String,
    @SerializedName("rocket_name")
    val rocketName: String,
    @SerializedName("rocket_type")
    val rocketType: String
)