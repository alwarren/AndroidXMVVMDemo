package com.ntxdroid.spacex.feature.mission

import android.content.Context
import android.content.Intent
import com.ntxdroid.spacex.core.platform.BaseActivity

/**
 * Created by Al Warren on 1/30/2019.
 */

class MissionDetailsActivity: BaseActivity() {
    override fun fragment() =
        MissionDetailsFragment.forMission(intent.getParcelableExtra(INTENT_EXTRA_PARAM_MISSION))

    companion object {
        const val INTENT_EXTRA_PARAM_MISSION = "com.ntxdroid.spacex.INTENT_PARAM_MISSION"

        fun callingIntent(context: Context, mission: MissionView): Intent {
            val intent = Intent(context, MissionDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_MISSION, mission)
            return intent
        }
    }
}