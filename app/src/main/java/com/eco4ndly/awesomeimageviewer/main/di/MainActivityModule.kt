package com.eco4ndly.awesomeimageviewer.main.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.eco4ndly.awesomeimageviewer.infra.di.scope.PerActivity
import com.eco4ndly.awesomeimageviewer.infra.di.vm.ViewModelFactory
import com.eco4ndly.awesomeimageviewer.main.MainActivityViewModel
import dagger.Module
import dagger.Provides

/**
 * A Sayan Porya code on 2020-01-18
 */

@Module
class MainActivityModule {

  @Provides
  @PerActivity
  fun providesMainViewModelFactory(mainActivityViewModel: MainActivityViewModel): ViewModelProvider.Factory {
    return ViewModelFactory(mainActivityViewModel)
  }

}