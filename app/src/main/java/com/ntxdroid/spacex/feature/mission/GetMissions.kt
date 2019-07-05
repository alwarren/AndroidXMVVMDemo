package com.ntxdroid.spacex.feature.mission

import com.ntxdroid.spacex.core.interactor.UseCase
import com.ntxdroid.spacex.domain.entity.mission.Mission

/**
 * Created by Al Warren on 1/24/2019.
 */

class GetMissions(private val repository: MissionRepository)
    : UseCase<List<Mission>, UseCase.None>() {

    override suspend fun run(params: None) = repository.missions()

    internal object EmptyParams : None()
}