package com.ntxdroid.spacex.feature.mission

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.ntxdroid.spacex.AndroidUnitTest
import com.ntxdroid.spacex.MainCoroutineRule
import com.ntxdroid.spacex.UnitTest
import com.ntxdroid.spacex.constants.Values
import com.ntxdroid.spacex.core.exception.Failure
import com.ntxdroid.spacex.core.exception.Failure.ServerError
import com.ntxdroid.spacex.core.functional.Either
import com.ntxdroid.spacex.core.functional.Either.Left
import com.ntxdroid.spacex.core.functional.Either.Right
import com.ntxdroid.spacex.core.functional.State
import com.ntxdroid.spacex.domain.entity.mission.Mission
import com.ntxdroid.spacex.mock.MockMissions
import com.ntxdroid.spacex.util.LiveDataTestUtil.getValue
import com.ntxdroid.spacex.util.MethodSpy
import edu.princeton.cs.algs4.StdOut
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.*

class MissionsViewModelTest : AndroidUnitTest() {
    @BeforeAll
    override fun beforeAll() {
        StdOut.println(Values.suiteTitle(Values.SUITE_TITLE_MISSIONS_VIEW_MODEL))
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

        lateinit var missionsViewModel: MissionsViewModel
        lateinit var missions: List<Mission>
        private val repository = FakeRepository()
        private val getMissions: GetMissions = GetMissions(repository)

        @BeforeAll
        fun beforeAll() {
            StdOut.println(Values.classTitle("Behavior"))
        }

        @BeforeEach
        fun before() {
            missions = Mission.mocks(2)
            repository.addMissions(missions)
            repository.disableFailure()
            missionsViewModel = MissionsViewModel(getMissions)
            Dispatchers.setMain(Dispatchers.Unconfined)
        }

        @DisplayName("ViewModel state")
        @Order(1)
        @Test fun view_model_state() {
            StdOut.print(Values.testTitle("ViewModel state"))

            mainCoroutineRule.pauseDispatcher()

            getValue(missionsViewModel.state) shouldBeInstanceOf State.Idle::class.java

            missionsViewModel.loadMissions()

            getValue(missionsViewModel.state) shouldBeInstanceOf State.InFlight::class.java

            mainCoroutineRule.resumeDispatcher()

            getValue(missionsViewModel.missions)

            getValue(missionsViewModel.state) shouldBeInstanceOf State.Complete::class.java

            showPassed()
        }

        @DisplayName("Repository Success")
        @Order(2)
        @Test fun repository_success() {
            StdOut.print(Values.testTitle("Repository Success"))

            missionsViewModel.loadMissions()

            getValue(missionsViewModel.missions) shouldEqual missions.map { MissionView(it) }

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

        lateinit var missionsViewModel: MissionsViewModel
        lateinit var missions: List<Mission>
        private val repository = FakeRepository()
        private val getMissions: GetMissions = GetMissions(repository)

        @BeforeEach
        fun before() {
            missions = Mission.mocks(2)
            repository.addMissions(missions)
            repository.disableFailure()
            missionsViewModel = MissionsViewModel(getMissions)
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

            missionsViewModel.loadMissions()

            getValue(missionsViewModel.failure) shouldEqual failure

            showPassed()
        }
    }

    override fun actualMethods() =
        MethodSpy.publicMethodNames(MissionsViewModel::class.java).sorted()

    override fun expectedMethods() =
        listOf("getMissions", "loadMissions", "refreshMissions").sorted()
}