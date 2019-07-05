package com.ntxdroid.spacex.domain.entity.launch

data class SecondStage(
    val block: Int?,
    val payloads: List<Payload>
)