package com.ntxdroid.spacex.feature.launch

import com.ntxdroid.spacex.core.interactor.UseCase
import com.ntxdroid.spacex.domain.entity.launch.Launch

/**
 * Created by Al Warren on 1/24/2019.
 */

class GetLaunches(private val repository: LaunchRepository)
    : UseCase<List<Launch>, UseCase.None>() {

    override suspend fun run(params: None) = repository.launches()

    internal object EmptyParams : None()
}