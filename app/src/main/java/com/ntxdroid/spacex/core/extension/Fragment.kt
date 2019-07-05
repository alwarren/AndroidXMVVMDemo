package com.ntxdroid.spacex.core.extension

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ntxdroid.spacex.core.platform.BaseActivity
import com.ntxdroid.spacex.core.platform.BaseFragment
import kotlinx.android.synthetic.main.activity_layout.*

/**
 * Created by Al Warren on 1/25/2019.
 */

inline fun <reified T : ViewModel> Fragment.viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

val BaseFragment.viewContainer: View get() = (activity as BaseActivity).fragmentContainer

fun Fragment.startBrowser(url: String): Boolean {
    val intent: Intent? = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    return if (intent != null) {
        startActivity(intent)
        true
    }
    else false
}
