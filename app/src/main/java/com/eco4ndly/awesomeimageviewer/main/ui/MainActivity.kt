package com.eco4ndly.awesomeimageviewer.main.ui

import android.os.Bundle
import com.eco4ndly.awesomeimageviewer.R
import com.eco4ndly.awesomeimageviewer.base.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun getFragmentContainerIfAny(): Int? {
        return main_activity_fragment_container.id
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addReplaceFragment(PhotoListFragment(), PhotoListFragment.TAG, false)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}
