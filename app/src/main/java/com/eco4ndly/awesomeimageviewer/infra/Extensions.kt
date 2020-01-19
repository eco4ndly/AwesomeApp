package com.eco4ndly.awesomeimageviewer.infra

import com.eco4ndly.awesomeimageviewer.infra.network.ApiResult
import com.eco4ndly.awesomeimageviewer.utils.Utils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retryWhen
import okhttp3.Call
import okhttp3.Request
import retrofit2.Retrofit
import java.io.IOException

/**
 * A Sayan Porya code on 2020-01-19
 */

@PublishedApi
internal inline fun Retrofit.Builder.callFactory(crossinline body: (Request) -> Call) =
    callFactory(object : Call.Factory {
        override fun newCall(request: Request): Call = body(request)
    })

@ExperimentalCoroutinesApi
fun <T : Any> Flow<ApiResult<T>>.applyCommonSideEffects() =
    retryWhen { cause, attempt ->
        when {
            (cause is IOException && attempt < Utils.MAX_RETRIES) -> {
                delay(Utils.getBackoffDelay(attempt))
                true
            }
            else -> {
                false
            }
        }
    }
        .onStart { emit(ApiResult.Loading(isLoading = true)) }
        .onCompletion { emit(ApiResult.Loading(isLoading = false)) }

fun Job?.cancelIfActive() {
    if (this?.isActive == true) {
        cancel()
    }
}