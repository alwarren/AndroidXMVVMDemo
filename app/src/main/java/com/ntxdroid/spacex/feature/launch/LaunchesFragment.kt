package com.ntxdroid.spacex.feature.launch

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ntxdroid.spacex.R
import com.ntxdroid.spacex.core.exception.Failure
import com.ntxdroid.spacex.core.exception.Failure.*
import com.ntxdroid.spacex.core.extension.consume
import com.ntxdroid.spacex.core.extension.observe
import com.ntxdroid.spacex.core.extension.viewModel
import com.ntxdroid.spacex.core.functional.State
import com.ntxdroid.spacex.core.functional.State.*
import com.ntxdroid.spacex.core.navigation.Navigation
import com.ntxdroid.spacex.core.platform.BaseFragment
import com.ntxdroid.spacex.core.platform.HttpFailure
import kotlinx.android.synthetic.main.launches_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class LaunchesFragment : BaseFragment(), KodeinAware,
    SwipeRefreshLayout.OnRefreshListener {
    override fun layoutId() = R.layout.launches_fragment

    override val kodein by kodein()

    private val viewModelFactory: LaunchesViewModel.Factory by instance()
    private lateinit var launchesViewModel: LaunchesViewModel

    private val launchesAdapter: LaunchesAdapter by instance()

    override fun onRefresh() {
        refreshLaunches()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        launchesViewModel = viewModel(viewModelFactory) {
            observe(state, ::handleState)
            observe(launches, ::renderLaunchList)
            observe(failure, ::handleFailure)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        launchesList.adapter = launchesAdapter
        launchesAdapter.clickListener = ::onItemClick
    }

    private fun onItemClick(launch: LaunchView) {
        Navigation.showLaunchDetails(activity!!, launch)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh.setOnRefreshListener(this)
        loadLaunches()
    }

    private fun loadLaunches() = launchesViewModel.loadLaunches()

    private fun refreshLaunches() = launchesViewModel.refreshLaunches()

    private fun handleState(state: State?) {
        when(state) {
            InFlight -> {
                swipeRefresh.isRefreshing = true
            }
            Complete, Idle, Gone -> {
                swipeRefresh.isRefreshing = false
            }
            else -> { swipeRefresh.isRefreshing = false }
        }
        this.state = state ?: Idle
    }

    private fun renderLaunchList(missions: List<LaunchView>?) {
        swipeRefresh.isRefreshing = false
        launchesAdapter.collection = missions.orEmpty()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            NetworkConnection -> renderFailure(R.string.failure_network_connection)
            ServerError -> renderFailure(R.string.failure_server_error)
            PermissionError -> renderFailure(R.string.failure_permission_error)
            NetworkOnMainThread -> renderFailure(R.string.failure_network_on_main_thread)
            NotImplemented -> renderFailure(R.string.failure_not_implemented)
            EmptyResult -> renderFailure(R.string.failure_empty_result)
            is HttpFailure -> renderHttpFailure(failure)
            null -> {}
        }
    }

    private fun renderHttpFailure(failure: HttpFailure) {
        failure.handleFailure {
            val message = getString(R.string.failure_server_http_error, it.code.toString())
            notifyWithAction(message, R.string.refresh, ::refreshLaunches,
                R.color.colorSnackAction, R.color.colorSnackText )
        }
    }

    private fun renderFailure(@StringRes message: Int) =
        notifyWithAction(message, R.string.refresh, ::refreshLaunches,
            R.color.colorSnackAction, R.color.colorSnackText )

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.menu_launches, menu)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> consume { refreshLaunches() }
            R.id.failure_network -> consume { handleFailure(NetworkConnection) }
            R.id.failure_server -> consume { handleFailure(ServerError) }
            R.id.failure_permissions -> consume { handleFailure(PermissionError) }
            R.id.failure_main_thread -> consume { handleFailure(NetworkOnMainThread) }
            R.id.failure_not_implemented -> consume { handleFailure(NotImplemented) }
            R.id.failure_empty_result -> consume { handleFailure(EmptyResult) }
            else -> true
        }
    }
}
