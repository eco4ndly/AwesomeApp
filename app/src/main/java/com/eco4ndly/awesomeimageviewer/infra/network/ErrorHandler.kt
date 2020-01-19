package com.eco4ndly.awesomeimageviewer.infra.network

import com.eco4ndly.awesomeimageviewer.app.AwesomeApp
import okhttp3.ResponseBody
import java.lang.Exception

/**
 * A Sayan Porya code on 2020-01-19
 */
object ErrorHandler {
    const val UNKNOWN_ERROR = "An unknown error occurred!"

    inline fun <reified T> parseError(responseBody: ResponseBody?): T? {

        responseBody?.string()?.run {
            try {
                return AwesomeApp.gson.fromJson(this, T::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return null
    }
}