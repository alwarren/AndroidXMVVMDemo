package com.ntxdroid.spacex.feature.mission

/**
 * Created by Al Warren on 1/30/2019.
 */

data class MissionDetailsView(
    val missionId: String,
    val missionName: String,
    val manufacturers: String,
    val payloadIds: String,
    val wikipedia: String,
    val website: String,
    val twitter: String,
    val description: String
) {
    constructor(mission: MissionView) : this(
        mission.missionId,
        mission.missionName,
        mission.manufacturers.joinToString(", "),
        mission.payloadIds.joinToString(", "),
        mission.wikipedia,
        mission.website,
        mission.twitter,
        mission.description
    )
}