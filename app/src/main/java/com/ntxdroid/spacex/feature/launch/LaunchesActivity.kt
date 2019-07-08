package com.ntxdroid.spacex.feature.launch

import android.content.Context
import android.content.Intent
import com.ntxdroid.spacex.core.platform.BaseActivity

class LaunchesActivity : BaseActivity() {
    override fun fragment() = LaunchesFragment()

    companion object {
        fun callingIntent(context: Context) = Intent(context, LaunchesActivity::class.java)
    }
}
