package com.ntxdroid.spacex.core.platform

import com.ntxdroid.spacex.core.exception.Failure.*
import com.ntxdroid.spacex.domain.network.HttpStatusCode

class HttpFailure(val status: HttpStatusCode = HttpStatusCode.Unknown) : FeatureFailure() {
    fun handleFailure(onStatus: (HttpStatusCode) -> Unit) {}
}