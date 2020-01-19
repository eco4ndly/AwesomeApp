package com.eco4ndly.awesomeimageviewer.app

import android.app.Application
import com.eco4ndly.awesomeimageviewer.infra.di.DaggerAppComponent
import com.google.gson.Gson
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * A Sayan Porya code on 2020-01-19
 */
class AwesomeApp: Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var _gson: Gson

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
        gson = _gson
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    companion object {
        lateinit var gson: Gson
    }
}