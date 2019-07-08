package com.ntxdroid.spacex.core.navigation

import android.content.Context
import com.ntxdroid.spacex.feature.launch.LaunchDetailsActivity
import com.ntxdroid.spacex.feature.launch.LaunchView
import com.ntxdroid.spacex.feature.launch.LaunchesActivity
import com.ntxdroid.spacex.feature.mission.MissionDetailsActivity
import com.ntxdroid.spacex.feature.mission.MissionView

/**
 * Created by Al Warren on 1/30/2019.
 */

object Navigation {
    fun showMissionDetails(context: Context, mission: MissionView) =
        context.startActivity(MissionDetailsActivity.callingIntent(context, mission))
    fun showLaunchDetails(context: Context, launch: LaunchView) =
        context.startActivity(LaunchDetailsActivity.callingIntent(context, launch))
    fun showLaunchesActivity(context: Context) =
        context.startActivity(LaunchesActivity.callingIntent(context))
}