package com.ntxdroid.spacex.domain.entity.rocket

data class PayloadWeight(
    val id: String,
    val name: String,
    val kg: Int,
    val lb: Int
)