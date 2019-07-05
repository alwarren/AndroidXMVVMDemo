package com.ntxdroid.spacex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ntxdroid.spacex.constants.Values
import com.ntxdroid.spacex.testextensions.InstantExecutorExtension
import edu.princeton.cs.algs4.StdOut
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(InstantExecutorExtension::class)
abstract class AndroidUnitTest {
    private var counter: Int = 0
    private var passed: Int = 0

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @BeforeAll
    abstract fun beforeAll()
    abstract fun actualMethods(): List<String>
    abstract fun expectedMethods(): List<String>

    @AfterEach
    fun afterEach() {
        ++counter
    }

    @AfterAll
    fun afterAll() {
        StdOut.println("End of Test Suite")
        StdOut.printf("Passed %s of %s tests\n\n", passed, counter)
    }

    protected fun showPassed() {
        passed++
        StdOut.println(Values.PASSED)
    }

    protected fun noTests() {
        StdOut.printf(Values.TEST_TITLE_FORMAT, Values.NO_TESTS)
        counter--
    }
}