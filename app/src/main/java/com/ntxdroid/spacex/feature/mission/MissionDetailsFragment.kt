package com.ntxdroid.spacex.feature.mission

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.ntxdroid.spacex.R
import com.ntxdroid.spacex.core.extension.startBrowser
import com.ntxdroid.spacex.core.extension.observe
import com.ntxdroid.spacex.core.extension.viewModel
import com.ntxdroid.spacex.core.platform.BaseFragment
import kotlinx.android.synthetic.main.mission_details_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.android.x.kodein

/**
 * Created by Al Warren on 1/30/2019.
 */

class MissionDetailsFragment : BaseFragment(), KodeinAware {
    override fun layoutId() = R.layout.mission_details_fragment

    override val kodein by kodein()

    private val viewModelFactory: MissionDetailsViewModel.Factory by instance()
    private lateinit var viewModel: MissionDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModel(viewModelFactory) {
            observe(missionDetails, ::renderMission)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (firstTimeCreated(savedInstanceState)) {
            viewModel.loadMission((arguments!![PARAM_MISSION] as MissionView))
        }
    }

    private fun renderMission(mission: MissionView?) {
        mission?.let {
            val details = MissionDetailsView(mission)
            missionName.text = details.missionName
            description.text = details.description
            renderUrl(wikipedia, details.wikipedia)
            renderUrl(website, details.website)
            renderUrl(twitter, details.twitter)
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
        private const val PARAM_MISSION = "param_mission"
        fun forMission(mission: MissionView): MissionDetailsFragment {
            val missionDetailsFragment = MissionDetailsFragment()
            val arguments = Bundle()
            arguments.putParcelable(PARAM_MISSION, mission)
            missionDetailsFragment.arguments = arguments
            return missionDetailsFragment
        }
    }
}