package com.ntxdroid.spacex.domain.entity.launch

data class FirstStage(
    val cores: List<Core>
) {
    companion object {
        val empty = FirstStage(emptyList())
    }
}