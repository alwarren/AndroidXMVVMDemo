package com.ntxdroid.spacex.feature.launch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Al Warren on 1/30/2019.
 */

class LaunchDetailsViewModel : ViewModel() {
    private val _launchDetails = MutableLiveData<LaunchView>()
    val launchDetails: LiveData<LaunchView>
        get() = _launchDetails

    fun loadLaunch(launchView: LaunchView) {
        _launchDetails.value = launchView
    }

    @Suppress("UNCHECKED_CAST")
    class Factory : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LaunchDetailsViewModel() as T
        }
    }
}