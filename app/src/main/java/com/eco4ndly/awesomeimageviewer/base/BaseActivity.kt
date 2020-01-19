package com.eco4ndly.awesomeimageviewer.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * A Sayan Porya code on 2020-01-19
 */
abstract class BaseActivity: AppCompatActivity() {
    fun addReplaceFragment(fragment: Fragment, tag: String, addToBackStack: Boolean = true) {
        getFragmentContainerIfAny()?.let {
            if (addToBackStack) {
                supportFragmentManager
                    .beginTransaction()
                    .add(it, fragment, tag)
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
            } else {
                supportFragmentManager
                    .beginTransaction()
                    .add(it, fragment, tag)
                    .commitAllowingStateLoss()
            }

        }
    }

    @LayoutRes
    protected abstract fun getFragmentContainerIfAny(): Int?
}