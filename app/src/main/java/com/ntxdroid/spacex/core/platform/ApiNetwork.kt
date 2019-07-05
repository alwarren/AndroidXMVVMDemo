package com.ntxdroid.spacex.core.platform

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Al Warren on 1/27/2019.
 */

class ApiNetwork(context: Context) : NetworkHandler {
    private val appContext = context.applicationContext

    override val isConnected: Boolean
        get() = isOnline()

    private fun isOnline(): Boolean {
        val connectivityService = appContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityService.activeNetworkInfo
        return networkInfo.isConnected
    }
}