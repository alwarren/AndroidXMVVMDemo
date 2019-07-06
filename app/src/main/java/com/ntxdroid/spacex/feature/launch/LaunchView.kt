package com.ntxdroid.spacex.feature.launch

import com.ntxdroid.spacex.domain.entity.launch.Launch

data class LaunchView(val flightNumber: Int,
                      val missionName: String,
                      val details: String?,
                      val launchDateUnix: Int,
                      val launchSite: String) {
    constructor(launch: Launch) : this(launch.flightNumber, launch.missionName, launch.details, launch.launchDateUnix, launch.launchSite.siteNameLong)
}