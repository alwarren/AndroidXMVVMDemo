package com.ntxdroid.spacex.feature.launch

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.ntxdroid.spacex.R
import com.ntxdroid.spacex.core.extension.observe
import com.ntxdroid.spacex.core.extension.startBrowser
import com.ntxdroid.spacex.core.extension.viewModel
import com.ntxdroid.spacex.core.platform.BaseFragment
import kotlinx.android.synthetic.main.launch_details_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * Created by Al Warren on 1/30/2019.
 */

class LaunchDetailsFragment : BaseFragment(), KodeinAware {
    override fun layoutId() = R.layout.launch_details_fragment

    override val kodein by kodein()

    private val viewModelFactory: LaunchDetailsViewModel.Factory by instance()
    private lateinit var viewModel: LaunchDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModel(viewModelFactory) {
            observe(launchDetails, ::renderLaunch)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (firstTimeCreated(savedInstanceState)) {
            viewModel.loadLaunch((arguments!![PARAM_LAUNCH] as LaunchView))
        }
    }

    private fun renderLaunch(launch: LaunchView?) {
        launch?.let {data ->
            missionName.text = data.missionName
            launchDate.text = data.launchDate
            launchSite.text = data.launchSite
            details.text = if(!data.details.isNullOrEmpty()) data.details
                else getString(R.string.no_details)
        }
    }

    private fun renderUrl(icon: ImageView, url: String?) {
        if (url.isNullOrEmpty())
            icon.visibility = View.GONE
        else {
            icon.visibility = View.VISIBLE
            icon.setOnClickListener {
                context?.run {
                    if (!startBrowser(url))
                        notify(R.string.snack_no_browser_available)
                }
            }
        }
    }

    companion object {
        private const val PARAM_LAUNCH = "param_launch"
        fun forLaunch(launch: LaunchView): LaunchDetailsFragment {
            val launchDetailsFragment = LaunchDetailsFragment()
            val arguments = Bundle()
            arguments.putParcelable(PARAM_LAUNCH, launch)
            launchDetailsFragment.arguments = arguments
            return launchDetailsFragment
        }
    }
}