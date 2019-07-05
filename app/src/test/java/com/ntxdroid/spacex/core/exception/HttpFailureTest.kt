package com.ntxdroid.spacex.core.exception

import com.ntxdroid.spacex.UnitTest
import com.ntxdroid.spacex.constants.Values
import org.amshove.kluent.shouldBeInstanceOf
import com.ntxdroid.spacex.core.exception.Failure.*
import com.ntxdroid.spacex.core.platform.HttpFailure
import com.ntxdroid.spacex.domain.network.HttpStatusCode
import com.ntxdroid.spacex.domain.network.HttpStatusCode.*
import com.ntxdroid.spacex.util.MethodSpy
import edu.princeton.cs.algs4.StdOut
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.*


class HttpFailureTest : UnitTest() {

    @BeforeAll
    override fun beforeAll() {
        StdOut.println(Values.suiteTitle(Values.SUITE_TITLE_HTTP_FAILURE))
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
        fun `ServerFailure is a Failure`() {
            StdOut.print(Values.testTitle("ServerFailure is a Failure"))
            HttpFailure() shouldBeInstanceOf (Failure::class.java)
            HttpFailure() shouldBeInstanceOf (FeatureFailure::class.java)
            showPassed()
        }

        @Order(2)
        @Test
        fun `given status code handle ServerFailure`() {
            StdOut.print(Values.testTitle("Given status code handle ServerFailure"))
            val status = HttpStatusCode.fromInt(999)
            HttpFailure(status)
                .handleFailure {
                    it shouldEqual Unknown
                }
            showPassed()
        }

        @Order(3)
        @Test
        fun `given status object handle ServerFailure`() {
            StdOut.print(Values.testTitle("Given status object handle ServerFailure"))
            val status = OK
            HttpFailure(status)
                .handleFailure { onFailure ->
                    onFailure shouldEqual OK
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

    override fun actualMethods(): List<String> {
        return MethodSpy.publicMethodNames(HttpFailure::class.java).sorted()
    }

    override fun expectedMethods(): List<String> {
        return listOf("getStatus", "handleFailure").sorted()
    }

}