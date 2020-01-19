package com.eco4ndly.awesomeimageviewer.main.di

import androidx.lifecycle.ViewModelProvider
import com.eco4ndly.awesomeimageviewer.infra.di.scope.PerFragment
import com.eco4ndly.awesomeimageviewer.infra.di.vm.ViewModelFactory
import com.eco4ndly.awesomeimageviewer.main.MainActivityViewModel
import com.eco4ndly.awesomeimageviewer.main.ui.adapter.PhotoListAdapter
import dagger.Module
import dagger.Provides

/**
 * A Sayan Porya code on 2020-01-19
 */

@Module
class PhotoListFragmentModule {

    /*@Provides
    @PerFragment
    fun providesMainViewModelFactory(mainActivityViewModel: MainActivityViewModel): ViewModelProvider.Factory {
        return ViewModelFactory(mainActivityViewModel)
    }*/
}