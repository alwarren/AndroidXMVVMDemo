package com.ntxdroid.spacex.domain.entity.landingpad

data class Location(
    val name: String,
    val region: String,
    val latitude: Double,
    val longitude: Double
)