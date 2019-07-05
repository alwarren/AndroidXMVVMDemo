/*
 * Copyright (c) 2019. Al Warren
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ntxdroid.spacex.core.platform

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ntxdroid.spacex.core.extension.inflate
import kotlin.properties.Delegates

/**
 * Created by Al Warren on 1/31/2019.
 */

abstract class AbstractListRecycler<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    abstract fun layoutId(): Int

    var collection: List<T> by Delegates.observable(emptyList()) {
            _, oldList:List<T>, newList:List<T> ->
                if (oldList !== newList) notifyDataSetChanged()
    }

    internal val diffCallback: DiffUtil.ItemCallback<List<T>> =
        object : DiffUtil.ItemCallback<List<T>>() {
            override fun areItemsTheSame(
                oldList: List<T>, newList: List<T>
            ): Boolean {
                return oldList === newList
            }

            override fun areContentsTheSame(
                oldList: List<T>, newList: List<T>
            ): Boolean {
                return oldList == newList
            }
        }

    internal var clickListener: (T) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        createViewHolder(parent.inflate(layoutId()))

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as Binder<T>).bind(collection[position], clickListener)
    }

    override fun getItemCount(): Int {
        return collection.size
    }

    fun isEmpty() = itemCount == 0

    abstract fun createViewHolder(view: View): RecyclerView.ViewHolder

    internal interface Binder<T> {
        fun bind(data: T, clickListener: (T) -> Unit)
    }
}
