package com.ntxdroid.spacex.core.functional

import com.ntxdroid.spacex.UnitTest
import com.ntxdroid.spacex.constants.Values
import com.ntxdroid.spacex.util.MethodSpy
import edu.princeton.cs.algs4.StdOut
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.*

class EitherTests : UnitTest() {
    private val testValue = "testValue"

    @BeforeAll
    override fun beforeAll() {
        StdOut.println(Values.suiteTitle(Values.SUITE_TITLE_EITHER))
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
            val expected = expectedMethods()
            val actual = actualMethods()
            Assertions.assertEquals(expected, actual)
            showPassed()
        }
    }

    @Nested
    @DisplayName("Behavior")
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class Functional {
        @BeforeAll
        fun setup() {
            StdOut.println(Values.classTitle("Behavior"))
        }

        @Order(1)
        @Test fun `Either Right is correct`() {
            StdOut.print(Values.testTitle("Either Right is correct"))

            val result = Either.Right(testValue)
            result shouldBeInstanceOf Either::class.java
            result.isRight shouldBe true
            result.isLeft shouldBe false
            result.either({},
                {
                    it shouldBeInstanceOf String::class.java
                    it shouldBeEqualTo testValue
                })
            showPassed()
        }

        @Order(2)
        @Test fun `Either Left is correct`() {
            StdOut.print(Values.testTitle("Either Left is correct"))

            val result = Either.Left(testValue)
            result shouldBeInstanceOf Either::class.java
            result.isLeft shouldBe true
            result.isRight shouldBe false
            result.either(
                {
                    it shouldBeInstanceOf String::class.java
                    it shouldBeEqualTo testValue
                }, {})
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
        MethodSpy.publicMethodNames(Either::class.java).sorted()

    override fun expectedMethods() =
        listOf("left", "right", "either", "isLeft", "isRight").sorted()
}