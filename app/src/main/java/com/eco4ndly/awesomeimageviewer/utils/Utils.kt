package com.eco4ndly.awesomeimageviewer.utils


/**
 * A Sayan Porya code on 2020-01-19
 */
object Utils {
    const val MAX_RETRIES = 3L
    private const val INITIAL_BACKOFF = 2000L

    fun getBackoffDelay(attempt: Long) = INITIAL_BACKOFF * (attempt + 1)
}