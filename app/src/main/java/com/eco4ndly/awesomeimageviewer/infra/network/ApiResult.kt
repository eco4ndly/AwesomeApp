package com.eco4ndly.awesomeimageviewer.infra.network

import com.eco4ndly.awesomeimageviewer.netwotk.apimodel.ErrorResponse

/**
 * A Sayan Porya code on 2020-01-19
 */
sealed class ApiResult<out T: Any> {
    data class Success<out T: Any>(val data: T): ApiResult<T>()
    data class Error(
        val exception: Throwable,
        val message: String = exception.message?: ErrorHandler.UNKNOWN_ERROR): ApiResult<Nothing>()
    data class Loading(val isLoading: Boolean): ApiResult<Nothing>()
}