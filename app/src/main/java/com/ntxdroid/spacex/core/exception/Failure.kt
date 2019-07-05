package com.ntxdroid.spacex.core.exception

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    object NetworkConnection: Failure()
    object ServerError: Failure()
    object PermissionError: Failure()
    object NetworkOnMainThread: Failure()
    object NotImplemented: Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure: Failure()
    object EmptyResult: FeatureFailure()
}

