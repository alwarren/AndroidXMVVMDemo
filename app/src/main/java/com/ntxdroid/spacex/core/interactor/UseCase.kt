package com.ntxdroid.spacex.core.interactor

import com.ntxdroid.spacex.core.functional.Either
import com.ntxdroid.spacex.core.exception.Failure
import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> where Type : Any{
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(
        params: Params,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) =
        uiScope.launch {
            onResult(withContext(Dispatchers.IO) {
                run(params)
            })
        }

    fun cancel() {
        job.cancel()
    }

    open class None
}