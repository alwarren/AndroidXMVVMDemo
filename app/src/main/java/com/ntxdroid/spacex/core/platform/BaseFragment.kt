package com.ntxdroid.spacex.core.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.ntxdroid.spacex.core.extension.setTextColor
import com.ntxdroid.spacex.core.extension.viewContainer
import com.ntxdroid.spacex.core.functional.State
import com.ntxdroid.spacex.core.functional.State.Gone

/**
 * Created by Al Warren on 1/25/2019.
 */

abstract class BaseFragment : Fragment() {
    abstract fun layoutId(): Int

    var state: State = Gone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        state = if(firstTimeCreated(savedInstanceState))
            State.Idle
        else
            State.get(savedInstanceState?.getInt(STATE_KEY)) ?: Gone
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_KEY, State.get(state))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutId(), container, false)

    internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

    internal fun notify(@StringRes message: Int) =
        Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()

    internal fun notifyWithAction(@StringRes message: Int, @StringRes actionText: Int,
                                  action: () -> Any, actionColor: Int?, messageColor: Int?) {
        val snackBar = Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(actionText) { action.invoke() }
        actionColor?.let { snackBar.setActionTextColor(it) }
        messageColor?.let { snackBar.setTextColor(it) }
        snackBar.show()
    }

    internal fun notifyWithAction(message: String, @StringRes actionText: Int,
                                  action: () -> Any, actionColor: Int?, messageColor: Int?) {
        val snackBar = Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(actionText) { action.invoke() }
        actionColor?.let { snackBar.setActionTextColor(it) }
        messageColor?.let { snackBar.setTextColor(it) }
        snackBar.show()
    }

    companion object {
        const val STATE_KEY: String = "state_key"
    }
}