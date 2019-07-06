package com.ntxdroid.spacex.feature.launch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ntxdroid.spacex.AndroidUnitTest
import com.ntxdroid.spacex.MainCoroutineRule
import com.ntxdroid.spacex.constants.Values
import com.ntxdroid.spacex.core.exception.Failure.ServerError
import com.ntxdroid.spacex.core.functional.State
import com.ntxdroid.spacex.domain.entity.launch.Launch
import com.ntxdroid.spacex.util.LiveDataTestUtil.getValue
import com.ntxdroid.spacex.util.MethodSpy
import edu.princeton.cs.algs4.StdOut
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Rule
import org.junit.jupiter.api.*

class LaunchesViewModelTest : AndroidUnitTest() {
    @BeforeAll
    override fun beforeAll() {
        StdOut.println(Values.suiteTitle(Values.SUITE_TITLE_LAUNCHES_VIEW_MODEL))
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
        @Test fun `expected methods`() {
            StdOut.print(Values.testTitle("expected methods"))
            actualMethods() shouldEqual expectedMethods()
            showPassed()
        }
    }

    @Nested
    @DisplayName("Behavior")
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @ExperimentalCoroutinesApi
    inner class Functional {
        // Set the main coroutines dispatcher for unit testing.
        @get:Rule var mainCoroutineRule = MainCoroutineRule()

        // Executes each task synchronously using Architecture Components.
        @get:Rule var instantExecutorRule = InstantTaskExecutorRule()

        lateinit var launchesViewModel: LaunchesViewModel
        lateinit var launches: List<Launch>
        private val repository = FakeRepository()
        private val getLaunches: GetLaunches = GetLaunches(repository)

        @BeforeAll
        fun beforeAll() {
            StdOut.println(Values.classTitle("Behavior"))
        }

        @BeforeEach
        fun before() {
            launches = listOf(Launch.empty, Launch.empty)
            repository.addLaunches(launches)
            repository.disableFailure()
            launchesViewModel = LaunchesViewModel(getLaunches)
            Dispatchers.setMain(Dispatchers.Unconfined)
        }

        @DisplayName("ViewModel state")
        @Order(1)
        @Test fun view_model_state() {
            StdOut.print(Values.testTitle("ViewModel state"))

            mainCoroutineRule.pauseDispatcher()

            getValue(launchesViewModel.state) shouldBeInstanceOf State.Idle::class.java

            launchesViewModel.loadLaunches()

            getValue(launchesViewModel.state) shouldBeInstanceOf State.InFlight::class.java

            mainCoroutineRule.resumeDispatcher()

            getValue(launchesViewModel.launches)

            getValue(launchesViewModel.state) shouldBeInstanceOf State.Complete::class.java

            showPassed()
        }

        @DisplayName("Repository Success")
        @Order(2)
        @Test fun repository_success() {
            StdOut.print(Values.testTitle("Repository Success"))

            launchesViewModel.loadLaunches()

            getValue(launchesViewModel.launches) shouldEqual launches.map { LaunchView(it) }

            showPassed()
        }
    }

    @Nested
    @DisplayName("Corner Cases")
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @ExperimentalCoroutinesApi
    inner class CornerCase {
        @get:Rule var mainCoroutineRule = MainCoroutineRule()

        // Executes each task synchronously using Architecture Components.
        @get:Rule var instantExecutorRule = InstantTaskExecutorRule()

        lateinit var launchesViewModel: LaunchesViewModel
        lateinit var launches: List<Launch>
        private val repository = FakeRepository()
        private val getLaunches: GetLaunches = GetLaunches(repository)

        @BeforeEach
        fun before() {
            launches = listOf(Launch.empty, Launch.empty)
            repository.addLaunches(launches)
            repository.disableFailure()
            launchesViewModel = LaunchesViewModel(getLaunches)
            Dispatchers.setMain(Dispatchers.Unconfined)
        }

        @BeforeAll
        fun setup() {
            StdOut.println(Values.classTitle("Corner Cases"))
        }


        @DisplayName("Repository Failure")
        @Order(1)
        @Test fun repository_failure() {
            StdOut.print(Values.testTitle("Repository Failure"))

            val failure = ServerError
            repository.setFailure(failure)

            launchesViewModel.loadLaunches()

            getValue(launchesViewModel.failure) shouldEqual failure

            showPassed()
        }
    }

    override fun actualMethods() =
        MethodSpy.publicMethodNames(LaunchesViewModel::class.java).sorted()

    override fun expectedMethods() =
        listOf("getLaunches", "loadLaunches", "refreshLaunches").sorted()
}