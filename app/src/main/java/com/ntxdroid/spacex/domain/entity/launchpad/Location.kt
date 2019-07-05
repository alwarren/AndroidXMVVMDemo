package com.ntxdroid.spacex.domain.entity.launchpad

data class Location(
    val name: String,
    val region: String,
    val latitude: Double,
    val longitude: Double
)