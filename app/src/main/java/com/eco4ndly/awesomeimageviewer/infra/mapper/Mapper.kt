package com.eco4ndly.awesomeimageviewer.infra.mapper

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * A Sayan Porya code on 2020-01-20
 */
interface Mapper<T> {
    suspend fun map(): T {
        return withContext(Dispatchers.Default) {
            getMapping()
        }
    }

    fun getMapping(): T
}