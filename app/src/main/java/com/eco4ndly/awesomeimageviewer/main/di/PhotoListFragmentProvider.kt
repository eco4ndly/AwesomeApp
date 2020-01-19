package com.eco4ndly.awesomeimageviewer.main.di

import com.eco4ndly.awesomeimageviewer.infra.di.scope.PerFragment
import com.eco4ndly.awesomeimageviewer.main.ui.PhotoListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * A Sayan Porya code on 2020-01-19
 */

@Module
abstract class PhotoListFragmentProvider {

    @ContributesAndroidInjector(modules = [PhotoListFragmentModule::class])
    @PerFragment
    abstract fun providePhotoListFragment(): PhotoListFragment
}