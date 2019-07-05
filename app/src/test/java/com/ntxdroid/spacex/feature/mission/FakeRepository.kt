package com.ntxdroid.spacex.feature.mission

import com.google.common.collect.Lists
import com.ntxdroid.spacex.core.exception.Failure
import com.ntxdroid.spacex.core.functional.Either
import com.ntxdroid.spacex.core.functional.Either.Left
import com.ntxdroid.spacex.core.functional.Either.Right
import com.ntxdroid.spacex.domain.entity.mission.Mission

class FakeRepository : MissionRepository {
    var tasksServiceData: LinkedHashMap<String, Mission> = LinkedHashMap()
    private var error: Failure = Failure.ServerError

    private var shouldReturnErrors = false

    override fun missions(): Either<Failure, List<Mission>> {
        return if (shouldReturnErrors) Left(Failure.ServerError)
        else Right(Lists.newArrayList(tasksServiceData.values))
    }

    override fun missionDetails(id: String): Either<Failure, Mission> {
        // not implemented
        return Right(Mission.empty)
    }

    fun getMissions(shouldReturnError: Boolean = false) = if (shouldReturnError) Left(error) else missions()

    fun addMissions(vararg missions: Mission) {
        for (mission in missions)
            tasksServiceData[mission.missionId] = mission
    }

    fun setFailure(failure: Failure) {
        shouldReturnErrors = true
        error = failure
    }

    fun disableFailure() {
        shouldReturnErrors = false
    }

    fun addMissions(missions: List<Mission>) {
        for (mission in missions)
            tasksServiceData[mission.missionId] = mission
    }
}