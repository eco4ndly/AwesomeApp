package com.eco4ndly.awesomeimageviewer.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.eco4ndly.awesomeimageviewer.R
import dagger.android.support.AndroidSupportInjection
import java.lang.RuntimeException

/**
 * A Sayan Porya code on 2020-01-19
 */
abstract class BaseFragment: Fragment() {
    protected fun addOrReplaceFragment(fragment: Fragment, tag: String, addToBackStack: Boolean = true) {
        activity?.let {
            if (it is BaseActivity) {
                it.addReplaceFragment(fragment, tag, addToBackStack)
            } else {
                throw RuntimeException("${activity?.title} Must extend ${BaseActivity::class.java.name}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        takeOff(savedInstanceState)
    }

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    protected abstract fun takeOff(savedInstanceState: Bundle?)
}