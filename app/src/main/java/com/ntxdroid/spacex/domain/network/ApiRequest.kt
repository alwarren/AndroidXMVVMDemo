package com.ntxdroid.spacex.domain.network

import com.ntxdroid.spacex.core.exception.Failure
import com.ntxdroid.spacex.core.platform.HttpFailure
import com.ntxdroid.spacex.core.functional.Either
import retrofit2.Call
import timber.log.Timber

/**
 * Created by Al Warren on 1/25/2019.
 */

interface ApiRequest {
    fun <T, R> requestFromApi(
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> =
        try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform(response.body() ?: default))
                false -> Either.Left(HttpFailure(HttpStatusCode.fromInt(response.code())))
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
}