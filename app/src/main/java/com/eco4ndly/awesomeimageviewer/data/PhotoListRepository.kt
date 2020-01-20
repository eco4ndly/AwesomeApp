package com.eco4ndly.awesomeimageviewer.data

import com.eco4ndly.awesomeimageviewer.infra.network.ApiResult
import com.eco4ndly.awesomeimageviewer.main.uimodel.Photo
import kotlinx.coroutines.flow.Flow

/**
 * A Sayan Porya code on 2020-01-20
 */
interface PhotoListRepository {
    suspend fun getPhotos(): Flow<ApiResult<List<Photo>>>
}