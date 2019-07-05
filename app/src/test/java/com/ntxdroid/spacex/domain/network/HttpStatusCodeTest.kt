package com.ntxdroid.spacex.domain.network

import com.ntxdroid.spacex.UnitTest
import com.ntxdroid.spacex.constants.Values
import com.ntxdroid.spacex.domain.network.HttpStatusCode.Unknown
import edu.princeton.cs.algs4.StdOut
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.*

class HttpStatusCodeTest : UnitTest() {
    @BeforeAll
    override fun beforeAll() {
        StdOut.println(Values.suiteTitle(Values.SUITE_TITLE_HTTP_STATUS_CODES))
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
        fun `verify expected names`() {
            StdOut.print(Values.testTitle("Verify Expected Names"))
            expectedNames() shouldEqual actualNames()
            showPassed()
        }

        @Order(2)
        @Test
        fun `verify expected values`() {
            StdOut.print(Values.testTitle("Verify Expected Values"))
            expectedValues() shouldEqual actualValues()
            showPassed()
        }
    }

    @Nested
    @DisplayName("Behavior")
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class Behvior {
        @BeforeAll
        fun setup() {
            StdOut.println(Values.classTitle("Behavior"))
        }

        @Order(3)
        @Test
        fun `invalid fromInt() parameter is type Unknown`() {
            StdOut.print(Values.testTitle("Invalid fromInt() parameter is Type Unknown"))
            HttpStatusCode.fromInt(999) shouldBe Unknown
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

    override fun expectedMethods(): List<String> {
        return expectedNames()
    }

    override fun actualMethods(): List<String> {
        return actualNames()
    }

    private fun actualNames(): List<String> {
        val values = ArrayList<String>()
        for (value in enumValues<HttpStatusCode>())
            values.add(value.name)

        return values.sorted()
    }

    private fun expectedNames(): List<String> {
        return listOf(
            "Continue", "SwitchingProtocols", "Processing", "OK", "Created", "Accepted",
            "NonAuthoritativeInformation", "NoContent", "ResetContent", "PartialContent",
            "MultiStatus", "AlreadyReported", "IMUsed", "MultipleChoices", "MovedPermanently",
            "Found", "SeeOther", "NotModified", "UseProxy", "TemporaryRedirect", "PermanentRedirect",
            "BadRequest", "Unauthorized", "PaymentRequired", "Forbidden", "NotFound",
            "MethodNotAllowed", "NotAcceptable", "ProxyAuthenticationRequired", "RequestTimeout",
            "Conflict", "Gone", "LengthRequired", "PreconditionFailed", "PayloadTooLarge",
            "URITooLong", "UnsupportedMediaType", "RangeNotSatisfiable", "ExpectationFailed",
            "IAmATeapot", "MisdirectedRequest", "UnprocessableEntity", "Locked", "FailedDependency",
            "UpgradeRequired", "PreconditionRequired", "TooManyRequests",
            "RequestHeaderFieldsTooLarge", "UnavailableForLegalReasons", "InternalServerError",
            "NotImplemented", "BadGateway", "ServiceUnavailable", "GatewayTimeout",
            "HTTPVersionNotSupported", "VariantAlsoNegotiates", "InsufficientStorage",
            "LoopDetected", "NotExtended", "NetworkAuthenticationRequired", "Unknown"
        ).sorted()
    }

    private fun expectedValues(): List<Int> {
        return listOf(
            100, 101, 102, 200, 201, 202, 203, 204, 205, 206, 207, 208, 226, 300,
            301, 302, 303, 304, 305, 307, 308, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409,
            410, 411, 412, 413, 414, 415, 416, 417, 418, 421, 422, 423, 424, 426, 428, 429, 431,
            451, 500, 501, 502, 503, 504, 505, 506, 507, 508, 510, 511, 0
        ).sorted()
    }

    private fun actualValues(): List<Int> {
        val values = ArrayList<Int>()
        for (value in HttpStatusCode.values())
            values.add(value.code)
        return values.sorted()
    }

}