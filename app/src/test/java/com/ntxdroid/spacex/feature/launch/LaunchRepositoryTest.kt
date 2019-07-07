package com.ntxdroid.spacex.feature.launch

import com.ntxdroid.spacex.UnitTest
import com.ntxdroid.spacex.constants.Values
import com.ntxdroid.spacex.core.exception.Failure
import com.ntxdroid.spacex.core.functional.Either
import com.ntxdroid.spacex.core.platform.HttpFailure
import com.ntxdroid.spacex.core.platform.NetworkHandler
import com.ntxdroid.spacex.domain.entity.launch.Launch
import com.ntxdroid.spacex.util.MethodSpy
import edu.princeton.cs.algs4.StdOut
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.*
import retrofit2.Call
import retrofit2.Response

class LaunchRepositoryTest : UnitTest() {
    private val launches = listOf(Launch.empty, Launch.empty)
    private val networkHandler = mockk<NetworkHandler>()
    private val call = mockk<Call<List<Launch>>>(relaxed = true)
    private val response = mockk<Response<List<Launch>>>()
    private val launchService = mockk<LaunchService>()

    @BeforeAll
    override fun beforeAll() {
        StdOut.println(Values.suiteTitle(Values.SUITE_TITLE_LAUNCH_REPOSITORY))
    }

    @BeforeEach
    fun setup() {
        every { networkHandler.isConnected } returns false
        every { response.isSuccessful } returns false
        every { response.body() } returns launches
        every { launchService.getAll() } returns call
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
        @Test
        fun `expected methods`() {
            StdOut.print(Values.testTitle("expected methods"))
            actualMethods() shouldEqual expectedMethods()
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
            StdOut.println(Values.classTitle("Behavior"))
        }

        @Order(1)
        @Test fun `return launches list`() {
            StdOut.print(Values.testTitle("return launches list"))
            every { networkHandler.isConnected } returns true
            every { response.isSuccessful } returns true
            getLaunches()
                .either({}, {it shouldEqual launches})
            showPassed()
        }

        @Order(2)
        @Test fun `return empty list`() {
            StdOut.print(Values.testTitle("return empty list"))
            every { networkHandler.isConnected } returns true
            every { response.isSuccessful } returns true
            every { response.body() } returns emptyList()
            getLaunches()
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
            getLaunches()
                .either({it shouldBeInstanceOf Failure.NetworkConnection::class}, {})
            showPassed()
        }

        @Order(4)
        @Test fun `response server error`() {
            StdOut.print(Values.testTitle("response server error"))
            every { networkHandler.isConnected } returns true
            every { response.isSuccessful } returns false
            every { response.code() } returns Values.HTTP_CODE_UNKNOWN
            getLaunches()
                .either({it shouldBeInstanceOf HttpFailure::class}, {})
            showPassed()
        }

        @Order(5)
        @Test fun `response exception`() {
            StdOut.print(Values.testTitle("response exception"))
            every { networkHandler.isConnected } returns true
            every { call.execute() } throws Exception()
            getLaunches()
                .either({it shouldBeInstanceOf Failure.ServerError::class}, {})
            showPassed()
        }
    }

    private fun getLaunches()
            : Either<Failure, List<Launch>> {
        val repository = LaunchRepository.Network(networkHandler, launchService)
        return repository.launches()
    }

    override fun actualMethods() =
        MethodSpy.publicMethodNames(LaunchRepository::class.java).sorted()

    override fun expectedMethods() =
        listOf("launches", "launchDetails").sorted()
}