package com.eco4ndly.awesomeimageviewer.infra.di

import android.app.Application
import com.eco4ndly.awesomeimageviewer.app.AwesomeApp
import com.eco4ndly.awesomeimageviewer.infra.di.module.ActivityBuilder
import com.eco4ndly.awesomeimageviewer.infra.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * A Sayan Porya code on 2020-01-19
 */

@Singleton
@Component(
    modules = [
      AndroidSupportInjectionModule::class,
      AppModule::class,
      ActivityBuilder::class
    ]
)
interface AppComponent {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(app: AwesomeApp)
}