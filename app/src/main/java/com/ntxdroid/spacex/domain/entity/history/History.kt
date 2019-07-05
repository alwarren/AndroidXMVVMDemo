package com.ntxdroid.spacex.domain.entity.history

import com.google.gson.annotations.SerializedName

// verified classes and types 1/22/2019
/**
 * https://api.spacexdata.com/v3/history
 * https://api.spacexdata.com/v3/history/{{id}}
 */
data class History(
    val id: Int,
    val title: String,
    @SerializedName("event_date_utc")
    val eventDateUtc: String,
    @SerializedName("event_date_unix")
    val eventDateUnix: Int,
    @SerializedName("flight_number")
    val flightNumber: Int?,
    val details: String,
    val links: Links
)