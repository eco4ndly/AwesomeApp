package com.eco4ndly.awesomeimageviewer.netwotk

import androidx.annotation.WorkerThread
import com.eco4ndly.awesomeimageviewer.main.uimodel.Photo
import com.eco4ndly.awesomeimageviewer.netwotk.apimodel.PhotoApi
import retrofit2.Response
import retrofit2.http.GET

/**
 * A Sayan Porya code on 2020-01-19
 */
interface WebService {
    @WorkerThread
    @GET("/v2/5e243b293400003b1e012b")
    suspend fun getPhotos(): Response<List<PhotoApi>>
}