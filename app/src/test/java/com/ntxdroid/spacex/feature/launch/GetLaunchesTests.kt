package com.ntxdroid.spacex.feature.launch

import com.ntxdroid.spacex.UnitTest
import com.ntxdroid.spacex.constants.Values
import com.ntxdroid.spacex.core.interactor.UseCase
import com.ntxdroid.spacex.core.platform.NetworkHandler
import com.ntxdroid.spacex.util.MethodSpy
import edu.princeton.cs.algs4.StdOut
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.*

class GetLaunchesTests : UnitTest() {
    private val networkHandler = mockk<NetworkHandler>(relaxed = true)
    private val missionService = mockk<LaunchService>()
    lateinit var repository: LaunchRepository
    private lateinit var getLaunchs: GetLaunches

    @BeforeAll
    override fun beforeAll() {
        StdOut.println(Values.suiteTitle(Values.SUITE_TITLE_LAUNCHES_USE_CASE))
        repository = LaunchRepository.Network(networkHandler, missionService)
        getLaunchs = GetLaunches(repository)
    }

    @Nested
    @DisplayName("API")
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class Api {
        @BeforeAll
        fun setup() {
            StdOut.println(Values.classTitle("API"))
        }

        @Order(1)
        @Test
        fun `expected methods`() {
            StdOut.print(Values.testTitle("expected methods"))
            actualMethods() shouldEqual expectedMethods()
            showPassed()
        }

    }

    @Nested
    @DisplayName("Behavior")
    @ExperimentalCoroutinesApi
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class Functional {
        @BeforeAll
        fun setup() {
            Dispatchers.setMain(Dispatchers.Unconfined)
            StdOut.println(Values.classTitle("Behavior"))
        }

        @Order(1)
        @Test
        fun `run is called`() {
            StdOut.print(Values.testTitle("run() is called"))
            runBlocking {
                getLaunchs.run(UseCase.None())
            }
            coVerify {
                repository.launches()
            }
            showPassed()
        }
    }

    @Nested
    @DisplayName("Corner Cases")
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class CornerCases {
        @BeforeAll
        fun setup() {
            StdOut.println(Values.classTitle("Corner Cases"))
        }

        @Test
        fun `no tests`() {
            noTests()
        }
    }

    override fun actualMethods() =
        MethodSpy.publicMethodNames(GetLaunches::class.java).sorted()

    override fun expectedMethods() =
        listOf("run").sorted()
}