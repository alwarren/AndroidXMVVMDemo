package com.ntxdroid.spacex.domain.entity.info

import com.google.gson.annotations.SerializedName

// verified classes and types 1/22/2019
/**
 * https://api.spacexdata.com/v3
 */
data class ApiInfo(
    @SerializedName("project_name")
    val projectName: String,
    val version: String,
    @SerializedName("project_link")
    val projectLink: String,
    val docs: String,
    val organization: String,
    @SerializedName("organization_link")
    val organizationLink: String,
    val description: String
)