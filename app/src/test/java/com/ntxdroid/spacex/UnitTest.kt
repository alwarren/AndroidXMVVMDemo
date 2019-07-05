package com.ntxdroid.spacex

import com.ntxdroid.spacex.constants.Values
import edu.princeton.cs.algs4.StdOut
import org.junit.jupiter.api.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class UnitTest {
    private var counter: Int = 0
    private var passed: Int = 0

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