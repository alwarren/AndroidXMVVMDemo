package com.ntxdroid.spacex.feature.mission

import com.ntxdroid.spacex.domain.network.ApiRequest
import com.ntxdroid.spacex.core.exception.Failure
import com.ntxdroid.spacex.core.functional.Either
import com.ntxdroid.spacex.core.functional.Either.Left
import com.ntxdroid.spacex.core.platform.NetworkHandler
import com.ntxdroid.spacex.domain.entity.mission.Mission


interface MissionRepository : ApiRequest {
    fun missions(): Either<Failure, List<Mission>>
    fun missionDetails(id: String): Either<Failure, Mission>

    class Network(
        private val networkHandler: NetworkHandler,
        private val service: MissionService
    ) : MissionRepository {

        override fun missions(): Either<Failure, List<Mission>> =
            when(networkHandler.isConnected) {
                true -> requestFromApi(
                    service.getAll(),
                    { it -> it.map { it } },
                    emptyList()
                )
                false -> Left(Failure.NetworkConnection)
            }

        override fun missionDetails(id: String): Either<Failure, Mission> =
            when(networkHandler.isConnected) {
                true -> requestFromApi(
                    service.getDetails(id),
                    { it },
                    Mission.empty
                )
                false -> Left(Failure.NetworkConnection)
            }

    }
}