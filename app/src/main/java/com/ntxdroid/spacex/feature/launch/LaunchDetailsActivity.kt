package com.ntxdroid.spacex.feature.launch

import android.content.Context
import android.content.Intent
import com.ntxdroid.spacex.core.platform.BaseActivity

/**
 * Created by Al Warren on 1/30/2019.
 */

class LaunchDetailsActivity: BaseActivity() {
    override fun fragment() =
        LaunchDetailsFragment.forLaunch(intent.getParcelableExtra(INTENT_EXTRA_PARAM_LAUNCH))

    companion object {
        const val INTENT_EXTRA_PARAM_LAUNCH = "com.ntxdroid.spacex.INTENT_PARAM_MISSION"

        fun callingIntent(context: Context, launch: LaunchView): Intent {
            val intent = Intent(context, LaunchDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_LAUNCH, launch)
            return intent
        }
    }
}