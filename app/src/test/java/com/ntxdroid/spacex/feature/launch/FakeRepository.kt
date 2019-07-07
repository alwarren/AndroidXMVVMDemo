package com.ntxdroid.spacex.feature.launch

import com.ntxdroid.spacex.core.exception.Failure
import com.ntxdroid.spacex.core.functional.Either
import com.ntxdroid.spacex.core.functional.Either.Left
import com.ntxdroid.spacex.core.functional.Either.Right
import com.ntxdroid.spacex.domain.entity.launch.Launch
import com.ntxdroid.spacex.feature.launch.LaunchRepository.Filter

class FakeRepository : LaunchRepository {
    var tasksServiceData: LinkedHashMap<Int, Launch> = LinkedHashMap()
    private var error: Failure = Failure.ServerError

    private var shouldReturnErrors = false

    override fun launches(filter: Filter): Either<Failure, List<Launch>> {
        return if (shouldReturnErrors) Left(Failure.ServerError)
        else Right(tasksServiceData.values.toList())
    }

    override fun launchDetails(id: Int): Either<Failure, Launch> {
        // not implemented
        return Right(Launch.empty)
    }

    fun getLaunches(shouldReturnError: Boolean = false) = if (shouldReturnError) Left(error) else launches()

    fun addLaunches(vararg launches: Launch) {
        for (launch in launches)
            tasksServiceData[launch.flightNumber] = launch
    }

    fun setFailure(failure: Failure) {
        shouldReturnErrors = true
        error = failure
    }

    fun disableFailure() {
        shouldReturnErrors = false
    }

    fun addLaunches(launches: List<Launch>) {
        for (id in 0 until launches.size)
            tasksServiceData[id] = launches[id]
    }
}