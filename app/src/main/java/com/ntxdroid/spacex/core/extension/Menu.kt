package com.ntxdroid.spacex.core.extension

/**
 * Created by Al Warren on 1/28/2019.
 */

inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}