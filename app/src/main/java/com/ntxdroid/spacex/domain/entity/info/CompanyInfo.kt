package com.ntxdroid.spacex.domain.entity.info

import com.google.gson.annotations.SerializedName

// verified classes and types 1/22/2019
/**
 * https://api.spacexdata.com/v3/info
 */
data class CompanyInfo(
    val name: String,
    val founder: String,
    val founded: Int,
    val employees: Int,
    val vehicles: Int,
    @SerializedName("launch_sites")
    val launchSites: Int,
    @SerializedName("test_sites")
    val testSites: Int,
    val ceo: String,
    val cto: String,
    val coo: String,
    @SerializedName("cto_propulsion")
    val ctoPropulsion: String,
    val valuation: Long,
    val headquarters: Headquarters,
    val links: Links,
    val summary: String
)