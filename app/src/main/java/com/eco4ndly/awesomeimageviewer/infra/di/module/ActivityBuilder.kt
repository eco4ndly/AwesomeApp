package com.eco4ndly.awesomeimageviewer.infra.di.module

import com.eco4ndly.awesomeimageviewer.infra.di.scope.PerActivity
import com.eco4ndly.awesomeimageviewer.main.di.MainActivityModule
import com.eco4ndly.awesomeimageviewer.main.di.PhotoDetailsProvider
import com.eco4ndly.awesomeimageviewer.main.ui.MainActivity
import com.eco4ndly.awesomeimageviewer.main.di.PhotoListFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * A Sayan Porya code on 2020-01-18
 */

@Module
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
            PhotoListFragmentProvider::class,
            PhotoDetailsProvider::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity
}