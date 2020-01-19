package com.eco4ndly.awesomeimageviewer.infra.di.module

import android.app.Application
import android.content.Context
import com.eco4ndly.awesomeimageviewer.BuildConfig
import com.eco4ndly.awesomeimageviewer.infra.callFactory
import com.eco4ndly.awesomeimageviewer.infra.di.scope.BaseUrl
import com.eco4ndly.awesomeimageviewer.infra.network.MockInterceptor
import com.eco4ndly.awesomeimageviewer.netwotk.WebService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * A Sayan Porya code on 2020-01-19
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.d(message)
            }
        })
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        logger: HttpLoggingInterceptor,
        @Named("Mock Interceptor") mockInterceptor: MockInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(mockInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, @BaseUrl baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .callFactory { okHttpClient.newCall(it) }
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun createWebService(retrofit: Retrofit): WebService {
        return retrofit.create(WebService::class.java)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String {
        return "https://www.google.com/"
    }

    @Provides
    @Singleton
    @Named("Mock Interceptor")
    fun provideMockInterceptor(): MockInterceptor {
        return MockInterceptor()
    }
}