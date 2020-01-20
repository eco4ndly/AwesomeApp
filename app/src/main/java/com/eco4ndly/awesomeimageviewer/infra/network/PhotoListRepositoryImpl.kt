package com.eco4ndly.awesomeimageviewer.infra.network

import com.eco4ndly.awesomeimageviewer.data.PhotoListRepository
import com.eco4ndly.awesomeimageviewer.infra.applyCommonSideEffects
import com.eco4ndly.awesomeimageviewer.infra.exception.NoResponseException
import com.eco4ndly.awesomeimageviewer.infra.mapper.photos.PhotosMapper
import com.eco4ndly.awesomeimageviewer.netwotk.WebService
import com.eco4ndly.awesomeimageviewer.netwotk.apimodel.ErrorResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * A Sayan Porya code on 2020-01-19
 */
class PhotoListRepositoryImpl @Inject constructor(private val websServices: WebService): PhotoListRepository  {

    override suspend fun getPhotos() = flow {
        websServices.getPhotos()
            .run {
                if (isSuccessful && body() != null) {
                    emit(ApiResult.Success(PhotosMapper(body()!!).map()))
                } else {
                    emit(ApiResult.Error(
                        NoResponseException(
                            ErrorHandler.parseError<ErrorResponse>(errorBody())?.message
                        )
                    ))
                }
            }
    }.applyCommonSideEffects().catch { emit(ApiResult.Error(it)) }
}