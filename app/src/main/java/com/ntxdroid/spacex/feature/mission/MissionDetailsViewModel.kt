package com.ntxdroid.spacex.feature.mission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Al Warren on 1/30/2019.
 */

class MissionDetailsViewModel : ViewModel() {
    private val _missionDetails = MutableLiveData<MissionView>()
    val missionDetails: LiveData<MissionView>
        get() = _missionDetails

    fun loadMission(missionView: MissionView) {
        _missionDetails.value = missionView
    }

    @Suppress("UNCHECKED_CAST")
    class Factory : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MissionDetailsViewModel() as T
        }
    }
}