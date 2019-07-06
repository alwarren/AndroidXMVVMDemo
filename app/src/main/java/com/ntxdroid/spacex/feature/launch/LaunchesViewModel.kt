package com.ntxdroid.spacex.feature.launch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ntxdroid.spacex.core.platform.BaseViewModel
import com.ntxdroid.spacex.domain.entity.launch.Launch
import com.ntxdroid.spacex.feature.launch.GetLaunches.EmptyParams

/**
 * Created by Al Warren on 1/25/2019.
 */

class LaunchesViewModel(private val getLaunches: GetLaunches) : BaseViewModel() {

    private val _launches = MutableLiveData<List<LaunchView>>()
    val launches: LiveData<List<LaunchView>>
        get() = _launches

    fun loadLaunches() {
        setInFlightState()
        getLaunches(EmptyParams) { onResult ->
            onResult.either(::handleFailure, ::handleLaunchList)
        }
    }

    fun refreshLaunches() {
        _launches.value = emptyList()
        loadLaunches()
    }

    private fun handleLaunchList(launches: List<Launch>) {
        setCompletedState()
        this._launches.value = launches.map { LaunchView(it) }
    }

    override fun onCleared() {
        getLaunches.cancel()
        super.onCleared()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val getLaunches: GetLaunches) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LaunchesViewModel(getLaunches) as T
        }
    }
}