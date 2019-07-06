package com.ntxdroid.spacex.domain.entity.launch

import com.google.gson.annotations.SerializedName

data class Fairings(
    val reused: Boolean,
    @SerializedName("recovery_attempt")
    val recoveryAttempt: Boolean?,
    val recovered: Boolean?,
    val ship: String?
) {
    companion object {
        val empty = Fairings(false, null, null, null)
    }
}