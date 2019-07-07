package com.ntxdroid.spacex.feature.launch

import com.ntxdroid.spacex.core.exception.Failure
import com.ntxdroid.spacex.core.functional.Either
import com.ntxdroid.spacex.core.platform.NetworkHandler
import com.ntxdroid.spacex.domain.entity.launch.Launch
import com.ntxdroid.spacex.domain.network.ApiRequest
import com.ntxdroid.spacex.feature.launch.LaunchRepository.Filter.*

interface LaunchRepository: ApiRequest {
    fun launches(filter: Filter = ALL): Either<Failure, List<Launch>>
    fun launchDetails(id: Int): Either<Failure, Launch?>

    class Network(
        private val networkHandler: NetworkHandler,
        private val service: LaunchService
    ) : LaunchRepository {

        override fun launches(filter: Filter): Either<Failure, List<Launch>> {
            return when (networkHandler.isConnected) {
                true -> requestFromApi(
                    when (filter) {
                        UPCOMING -> service.getUpcoming()
                        PAST -> service.getPast()
                        ALL -> service.getAll()
                    },
                    { it -> it.map { it } },
                    emptyList()
                )
                false -> Either.Left(Failure.NetworkConnection)
            }
        }

        override fun launchDetails(id: Int): Either<Failure, Launch> =
            when(networkHandler.isConnected) {
                true -> requestFromApi(
                    service.getDetails(id),
                    { it },
                    Launch.empty
                )
                false -> Either.Left(Failure.NetworkConnection)
            }
    }

    enum class Filter{
        PAST, UPCOMING, ALL
    }
}
