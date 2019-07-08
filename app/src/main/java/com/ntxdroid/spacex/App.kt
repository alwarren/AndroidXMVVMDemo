package com.ntxdroid.spacex

import android.app.Application
import com.ntxdroid.spacex.core.platform.Api
import com.ntxdroid.spacex.core.platform.ApiNetwork
import com.ntxdroid.spacex.core.platform.NetworkHandler
import com.ntxdroid.spacex.feature.launch.*
import com.ntxdroid.spacex.feature.mission.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber

class App : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))
        bind<NetworkHandler>() with singleton { ApiNetwork(instance()) }
        bind<MissionService>() with singleton { Api.service(MissionService::class.java) }
        bind<LaunchService>() with singleton { Api.service(LaunchService::class.java) }
        bind<MissionRepository>() with singleton { MissionRepository.Network(instance(), instance()) }
        bind<LaunchRepository>() with singleton { LaunchRepository.Network(instance(), instance()) }

        bind() from singleton { GetMissions(instance()) }
        bind() from singleton { GetLaunches(instance()) }
        bind() from singleton { LaunchesAdapter() }
        bind() from singleton { MissionsAdapter() }
        bind() from provider { MissionsViewModel.Factory(instance()) }
        bind() from provider { MissionDetailsViewModel.Factory() }
        bind() from provider { LaunchesViewModel.Factory(instance()) }
        bind() from provider { LaunchDetailsViewModel.Factory() }
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}