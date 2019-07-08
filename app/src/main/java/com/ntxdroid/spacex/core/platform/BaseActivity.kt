package com.ntxdroid.spacex.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ntxdroid.spacex.R.id
import com.ntxdroid.spacex.R.layout
import com.ntxdroid.spacex.core.extension.inTransaction
import com.ntxdroid.spacex.core.extension.invisible
import kotlinx.android.synthetic.main.activity_layout.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by Al Warren on 1/25/2019.
 */

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_layout)
        setSupportActionBar(toolbar)
        addFragment(savedInstanceState)

        fab.setOnClickListener {}
        fab.invisible()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(id.fragmentContainer, fragment())
        }

    abstract fun fragment(): BaseFragment
}