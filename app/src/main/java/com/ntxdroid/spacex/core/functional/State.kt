/*
 * Copyright (c) 2019 Al Warren
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package com.ntxdroid.spacex.core.functional

/**
 * Created by Al Warren on 1/31/2019.
 */

sealed class State {
    object InFlight: State()
    object Complete: State()
    object Idle: State()
    object Gone: State()

    companion object {
        fun get(id: Int?):State? =
                when(id) {
                    1 -> InFlight
                    2 -> Complete
                    3 -> Idle
                    4 -> Gone
                    else -> null
                }
        fun get(state: State) =
                when(state) {
                    InFlight -> 1
                    Complete -> 2
                    Idle -> 3
                    Gone -> 4
                }
    }
}