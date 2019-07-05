package com.ntxdroid.spacex.feature.mission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ntxdroid.spacex.core.platform.BaseViewModel
import com.ntxdroid.spacex.domain.entity.mission.Mission
import com.ntxdroid.spacex.feature.mission.GetMissions.EmptyParams

/**
 * Created by Al Warren on 1/25/2019.
 */

class MissionsViewModel(private val getMissions: GetMissions) : BaseViewModel() {

    private val _missions = MutableLiveData<List<MissionView>>()
    val missions: LiveData<List<MissionView>>
        get() = _missions

    fun loadMissions() {
        setInFlightState()
        getMissions(EmptyParams) { onResult ->
            onResult.either(::handleFailure, ::handleMissionList)
        }
    }

    fun refreshMissions() {
        _missions.value = emptyList()
        loadMissions()
    }

    private fun handleMissionList(missions: List<Mission>) {
        setCompletedState()
        this._missions.value = missions.map { MissionView(it) }
    }

    override fun onCleared() {
        getMissions.cancel()
        super.onCleared()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val getMissions: GetMissions) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MissionsViewModel(getMissions) as T
        }
    }
}