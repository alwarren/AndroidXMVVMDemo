package com.ntxdroid.spacex.feature.mission

import com.ntxdroid.spacex.core.interactor.UseCase
import com.ntxdroid.spacex.domain.entity.mission.Mission
import com.ntxdroid.spacex.feature.mission.GetMission.Params

/**
 * Created by Al Warren on 1/24/2019.
 */

class GetMission(private val repository: MissionRepository)
    : UseCase<Mission, Params>() {

    override suspend fun run(params: Params) =
        repository.missionDetails(params.missionId)

    data class Params(val missionId: String)
}