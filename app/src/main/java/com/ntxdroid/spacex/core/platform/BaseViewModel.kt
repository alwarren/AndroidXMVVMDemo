package com.ntxdroid.spacex.core.platform

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ntxdroid.spacex.core.exception.Failure
import com.ntxdroid.spacex.core.functional.State
import com.ntxdroid.spacex.core.functional.State.*

/**
 * Base ViewModel class with default Failure handling and state.
 * @see ViewModel
 * @see Failure
 */
abstract class BaseViewModel : ViewModel() {

    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure: LiveData<Failure>
        get() = _failure

    private val _state = MutableLiveData<State>().apply { postValue(Idle) }
    val state: LiveData<State>
        get() = _state

    protected fun handleFailure(failure: Failure) {
        _failure.value = failure
        setCompletedState()
    }

    protected fun setInFlightState() {
        _state.value = InFlight
    }

    protected fun setCompletedState() {
        _state.value = Complete
    }

    protected fun setGoneState() {
        _state.value = Gone
    }

    protected fun setIdleState() {
        _state.value = Idle
    }

    override fun onCleared() {
        setGoneState()
        super.onCleared()
    }
}