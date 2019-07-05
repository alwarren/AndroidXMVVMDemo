package com.ntxdroid.spacex.constants

import java.text.SimpleDateFormat
import java.util.Date

class Values {
    companion object {
        const val PASSED = "PASSED"
        private const val TEST_CLASS_FORMAT = "  %s"
        const val TEST_TITLE_FORMAT = "    %s\n"
        private const val TEST_FORMAT = "    %s => "
        const val NO_TESTS = "No Tests"

        const val HTTP_CODE_UNKNOWN = 999

        const val SUITE_TITLE_MISSIONS_REPOSITORY = "Mission Repository"
        const val SUITE_TITLE_MISSIONS_VIEW_MODEL = "Missions ViewModel"
        const val SUITE_TITLE_EITHER = "Either"
        const val SUITE_TITLE_MISSIONS_USE_CASE = "Missions Use Case"
        const val SUITE_TITLE_USE_CASE = "Abstract Use Case"
        const val SUITE_TITLE_HTTP_FAILURE = "Http Failures"
        const val SUITE_TITLE_HTTP_STATUS_CODES = "Http Status Codes"

        private fun dateTime(): String {
            val pattern = "yyyy-MM-dd h:mm a"
            val simpleDateFormat = SimpleDateFormat(pattern)
            return simpleDateFormat.format(Date())
        }

        fun suiteTitle(title: String): String =
            String.format("\n%s Test Suite " + dateTime() + "\n", title)

        fun classTitle(title: String): String =
            String.format(TEST_CLASS_FORMAT, title)

        fun testTitle(title: String): String =
            String.format(TEST_FORMAT, title)
    }
}
