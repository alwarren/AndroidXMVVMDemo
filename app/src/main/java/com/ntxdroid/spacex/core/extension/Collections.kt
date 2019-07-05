package com.ntxdroid.spacex.core.extension

/**
 * Created by Al Warren on 2/2/2019.
 */

fun <T> List<T>.repeatUntilSize(maxSize: Int = 0): List<T> {
    if (size == maxSize) return this
    if (maxSize == 0) return emptyList()
    if (maxSize < size) return this.take(maxSize)

    val remainder = maxSize % size
    val repeats = (maxSize - remainder) / size

    val container = mutableListOf<T>()

    (1..repeats).forEach { _ -> this.forEach { container.add(it) } }
    (0 until remainder).forEach { container.add(this[it]) }

    return container
}