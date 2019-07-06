package com.ntxdroid.spacex.domain.entity.launch

import com.google.gson.annotations.SerializedName

// verified classes and types 1/22/2019
/**
 * https://api.spacexdata.com/v3/launches
 * https://api.spacexdata.com/v3/launches/{{flight_number}}
 * https://api.spacexdata.com/v3/launches/past
 * https://api.spacexdata.com/v3/launches/upcoming
 * https://api.spacexdata.com/v3/launches/latest
 * https://api.spacexdata.com/v3/launches/next
 */
data class Launch(
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("mission_name")
    val missionName: String,
    @SerializedName("mission_id")
    val missionId: List<String>,
    val upcoming: Boolean,
    @SerializedName("launch_year")
    val launchYear: String,
    @SerializedName("launch_date_unix")
    val launchDateUnix: Int,
    @SerializedName("launch_date_utc")
    val launchDateUtc: String,
    @SerializedName("launch_date_local")
    val launchDateLocal: String,
    @SerializedName("is_tentative")
    val isTentative: Boolean,
    @SerializedName("tentative_max_precision")
    val tentativeMaxPrecision: String,
    val tbd: Boolean,
    @SerializedName("launch_window")
    val launchWindow: Int?,
    val rocket: Rocket,
    val ships: List<String>,
    val telemetry: Telemetry,
    @SerializedName("launch_site")
    val launchSite: LaunchSite,
    @SerializedName("launch_success")
    val launchSuccess: Boolean,
    @SerializedName("launch_failure_details")
    val launchFailureDetails: LaunchFailureDetails?,
    val links: Links,
    val details: String?,
    @SerializedName("static_fire_date_utc")
    val staticFireDateUtc: String?,
    @SerializedName("static_fire_date_unix")
    val staticFireDateUnix: Int?,
    val timeline: Map<String, Int>?
) {
    companion object {
        val empty = Launch(0, "", emptyList(), false,
            "", 0, "", "", false, "",
            false, null, Rocket.empty, emptyList(), Telemetry.empty, LaunchSite.empty,
            false, null, Links.empty, null, null,
            null, null)
    }
}