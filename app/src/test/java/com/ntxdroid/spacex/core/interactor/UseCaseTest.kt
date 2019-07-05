package com.ntxdroid.spacex.core.interactor

import com.ntxdroid.spacex.UnitTest
import com.ntxdroid.spacex.constants.Values
import com.ntxdroid.spacex.core.exception.Failure
import com.ntxdroid.spacex.core.functional.Either
import com.ntxdroid.spacex.core.functional.Either.Right
import com.ntxdroid.spacex.util.MethodSpy
import edu.princeton.cs.algs4.StdOut
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.*

class UseCaseTest : UnitTest() {
    private val TYPE_TEST = "Test"
    private val TYPE_PARAM = "ParamTest"

    private val useCase = MyUseCase()

    @BeforeAll
    override fun beforeAll() {
        StdOut.println(Values.suiteTitle(Values.SUITE_TITLE_USE_CASE))
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
    inner class Functional {

        @BeforeAll
        @ExperimentalCoroutinesApi
        fun setup() {
            Dispatchers.setMain(Dispatchers.Unconfined)
            StdOut.println(Values.classTitle("Behavior"))
        }

        @Order(1)
        @Test fun `run() Right is correct`() {
            StdOut.print(Values.testTitle("run() Right is correct"))
            val params = MyParams(TYPE_PARAM)
            val result = runBlocking { useCase.run(params) }

            result shouldEqual Right(MyType(TYPE_TEST))
            showPassed()
        }

        @Test fun `should return correct data when executing use case`() {
            StdOut.print(Values.testTitle("should return correct data when executing use case"))

            var result: Either<Failure, MyType>? = null

            val params = MyParams("Test")
            val onResult = { myResult: Either<Failure, MyType> -> result = myResult }

            runBlocking {
                useCase(params, onResult).invokeOnCompletion {
                    result shouldEqual Right(MyType(TYPE_TEST))
                    showPassed()
                }
            }

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

    data class MyType(val name: String)
    data class MyParams(val name: String)

    private inner class MyUseCase : UseCase<MyType, MyParams>() {
        override suspend fun run(params: MyParams) = Right(MyType(TYPE_TEST))
    }

    override fun actualMethods() = MethodSpy.publicMethodNames(UseCase::class.java).sorted()
    override fun expectedMethods() = listOf("invoke", "run", "cancel").sorted()
}