package com.ntxdroid.spacex.domain.entity.mission

import com.google.gson.annotations.SerializedName

// verified classes and types 1/22/2019
/**
 * https://api.spacexdata.com/v3/launches
 * https://api.spacexdata.com/v3/launches/{{mission_id}}
 */
data class Mission(
    @SerializedName("mission_name")
    val missionName: String,
    @SerializedName("mission_id")
    val missionId: String,
    val manufacturers: List<String>,
    @SerializedName("payload_ids")
    val payloadIds: List<String>,
    val wikipedia: String,
    val website: String,
    val twitter: String?,
    val description: String
) {
    companion object {
        val empty = Mission("", "", emptyList(), emptyList(),
                "", "", null, "")

        fun mocks(count: Int): List<Mission> {
            val mutableList = mutableListOf<Mission>()
            for (id in 0 until count) {
                mutableList.add(
                    Mission("Mission ${id+1}", "Id ${id+1}", emptyList(), emptyList(),
                        "", "", null, "")
                )
            }
            return mutableList.toList()
        }
    }

}