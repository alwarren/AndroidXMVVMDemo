package com.ntxdroid.spacex.feature.mission

import com.ntxdroid.spacex.UnitTest
import com.ntxdroid.spacex.constants.Values
import com.ntxdroid.spacex.domain.entity.mission.Mission
import com.ntxdroid.spacex.util.MethodSpy
import edu.princeton.cs.algs4.StdOut
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.*

class MissionViewTest : UnitTest() {
    @BeforeAll
    override fun beforeAll() {
        StdOut.println(Values.suiteTitle(Values.SUITE_TITLE_MISSION_VIEW))
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
        fun setup() {
            StdOut.println(Values.classTitle("Behavior"))
        }

        @Test
        fun `can map mission to view`() {
            StdOut.print(Values.testTitle("can map Mission to view"))
            MissionView(Mission.empty)
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
        return MethodSpy.publicMethodNames(MissionView::class.java).sorted()
    }

    override fun expectedMethods(): List<String> {
        return listOf("component1", "component2", "component3", "component4", "component5", "component6", "component7",
            "component8", "copy", "describeContents", "equals", "getDescription", "getManufacturers", "getMissionId",
            "getMissionName", "getPayloadIds", "getTwitter", "getWebsite", "getWikipedia", "hashCode",
            "toString", "writeToParcel").sorted()
    }

}