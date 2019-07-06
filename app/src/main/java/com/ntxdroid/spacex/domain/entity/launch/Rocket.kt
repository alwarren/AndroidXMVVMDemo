package com.ntxdroid.spacex.domain.entity.launch

import com.google.gson.annotations.SerializedName

data class Rocket(
    @SerializedName("rocket_id")
    val rocketId: String,
    @SerializedName("rocket_name")
    val rocketName: String,
    @SerializedName("rocket_type")
    val rocketType: String,
    @SerializedName("first_stage")
    val firstStage: FirstStage,
    @SerializedName("second_stage")
    val secondStage: SecondStage,
    val fairings: Fairings?
) {
    companion object {
        val empty = Rocket("", "", "", FirstStage.empty,
            SecondStage.empty, null)
    }
}