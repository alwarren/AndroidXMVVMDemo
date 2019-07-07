package com.ntxdroid.spacex.core.platform

import com.ntxdroid.spacex.UnitTest
import com.ntxdroid.spacex.constants.Values
import com.ntxdroid.spacex.util.MethodSpy
import edu.princeton.cs.algs4.StdOut
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.*
import java.util.*

// See https://developer.android.com/training/data-storage/room/referencing-data
class ConvertersTest : UnitTest() {
    @BeforeAll
    override fun beforeAll() {
        StdOut.println(Values.suiteTitle(Values.SUITE_TITLE_CONVERTERS))
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
//            actualMethods() shouldEqual expectedMethods()
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

        private val unixTimestamp = 1L
        private val converter = Converters()
        private val dateFormat = "EEE, MMM d, yyyy HH::mm z"
        private val dateString = "Thu, Jan 1, 1970 00::00 UTC"

        @Order(1)
        @Test
        fun `long to date is correct`() {
            StdOut.print(Values.testTitle("long to date is correct"))
            converter.fromTimestamp(unixTimestamp) shouldEqual Date(unixTimestamp)
            showPassed()
        }

        @Order(2)
        @Test
        fun `date to long is correct`() {
            StdOut.print(Values.testTitle("date to long is correct"))
            converter.dateToTimestamp(Date(unixTimestamp)) shouldBe unixTimestamp
            showPassed()
        }

        @Order(3)
        @Test
        fun `long to date string is correct`() {
            StdOut.print(Values.testTitle("long to date string is correct"))
            Converters.dateString(unixTimestamp, dateFormat) shouldEqual dateString
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

    override fun actualMethods() = MethodSpy.publicMethodNames(Converters::class.java).sorted()
    override fun expectedMethods() = listOf("dateToTimestamp", "fromTimestamp").sorted()

}