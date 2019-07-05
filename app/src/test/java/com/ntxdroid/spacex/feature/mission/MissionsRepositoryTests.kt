package com.ntxdroid.spacex.feature.mission

import com.ntxdroid.spacex.UnitTest
import com.ntxdroid.spacex.constants.Values
import com.ntxdroid.spacex.core.exception.Failure
import com.ntxdroid.spacex.core.exception.Failure.*
import com.ntxdroid.spacex.core.platform.HttpFailure
import com.ntxdroid.spacex.core.functional.Either
import com.ntxdroid.spacex.core.platform.NetworkHandler
import com.ntxdroid.spacex.domain.entity.mission.Mission
import com.ntxdroid.spacex.mock.MockMissions
import com.ntxdroid.spacex.util.MethodSpy
import edu.princeton.cs.algs4.StdOut
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.*
import retrofit2.Call
import retrofit2.Response
import kotlin.Exception

class MissionsRepositoryTests : UnitTest() {
    private val networkHandler = mockk<NetworkHandler>()
    private val call = mockk<Call<List<Mission>>>(relaxed = true)
    private val response = mockk<Response<List<Mission>>>()
    private val missionService = mockk<MissionService>()

    @BeforeAll
    override fun beforeAll() {
        StdOut.println(Values.suiteTitle(Values.SUITE_TITLE_MISSIONS_REPOSITORY))
    }

    @BeforeEach
    fun setup() {
        every { networkHandler.isConnected } returns false
        every { response.isSuccessful } returns false
        every { response.body() } returns MockMissions.list
        every { missionService.getAll() } returns call
        every { call.execute() } returns response
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
    @DisplayName("Functional")
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class Functional {
        @BeforeAll
        fun setup() {
            StdOut.println(Values.classTitle("Functional"))
        }

        @Order(1)
        @Test fun `return missions list`() {
            StdOut.print(Values.testTitle("return missions list"))
            every { networkHandler.isConnected } returns true
            every { response.isSuccessful } returns true
            getMissions(networkHandler, missionService)
                .either({}, {it shouldEqual MockMissions.list})
            showPassed()
        }

        @Order(2)
        @Test fun `return empty list`() {
            StdOut.print(Values.testTitle("return empty list"))
            every { networkHandler.isConnected } returns true
            every { response.isSuccessful } returns true
            every { response.body() } returns emptyList()
            getMissions(networkHandler, missionService)
            getMissions(networkHandler, missionService)
                .either({}, {it shouldEqual emptyList()})
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

        @Order(3)
        @Test fun `network failure`() {
            StdOut.print(Values.testTitle("network failure"))
            every { networkHandler.isConnected } returns false
            getMissions(networkHandler, missionService)
            getMissions(networkHandler, missionService)
                .either({it shouldBeInstanceOf NetworkConnection::class}, {})
            showPassed()
        }

        @Order(4)
        @Test fun `response server error`() {
            StdOut.print(Values.testTitle("response server error"))
            every { networkHandler.isConnected } returns true
            every { response.isSuccessful } returns false
            every { response.code() } returns Values.HTTP_CODE_UNKNOWN
            getMissions(networkHandler, missionService)
                .either({it shouldBeInstanceOf HttpFailure::class}, {})
            showPassed()
        }

        @Order(5)
        @Test fun `response exception`() {
            StdOut.print(Values.testTitle("response exception"))
            every { networkHandler.isConnected } returns true
            every { call.execute() } throws Exception()
            getMissions(networkHandler, missionService)
                .either({it shouldBeInstanceOf ServerError::class}, {})
            showPassed()
        }
    }

    @Nested
    @DisplayName("Behavior")
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class Behavior {
        @BeforeAll
        fun setup() {
            every { networkHandler.isConnected } returns true
            every { response.isSuccessful } returns true
            every { response.body() } returns emptyList()
            every { missionService.getAll() } returns call
            every { call.execute() } returns response
            getMissions(networkHandler, missionService)
            StdOut.println(Values.classTitle("Behavior"))
        }

        @Order(1)
        @Test
        fun `networkHandler isConnected is called`() {
            StdOut.print(Values.testTitle("NetworkHandler isConnected is called"))
            verify { networkHandler.isConnected }
            showPassed()
        }

        @Order(2)
        @Test
        fun `MissionService getAll() is called`() {
            StdOut.print(Values.testTitle("MissionService getAll() is called"))
            verify { missionService.getAll() }
            showPassed()
        }

        @Order(3)
        @Test
        fun `call execute() is called`() {
            StdOut.print(Values.testTitle("Call execute() is called"))
            verify { call.execute() }
            showPassed()
        }
    }

    private fun getMissions(networkHandler: NetworkHandler, missionsService: MissionService)
            : Either<Failure, List<Mission>> {
        val repository = MissionRepository.Network(networkHandler, missionsService)
        return repository.missions()
    }

    override fun actualMethods() =
        MethodSpy.publicMethodNames(MissionRepository::class.java).sorted()

    override fun expectedMethods() =
        listOf("missions", "missionDetails").sorted()
}