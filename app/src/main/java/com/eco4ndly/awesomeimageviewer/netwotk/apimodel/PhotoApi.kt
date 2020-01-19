package com.eco4ndly.awesomeimageviewer.netwotk.apimodel

import com.google.gson.annotations.SerializedName

/**
 * A Sayan Porya code on 2020-01-20
 */
data class PhotoApi(
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("explanation")
    val explanation: String?,
    @SerializedName("hdurl")
    val hdurl: String?,
    @SerializedName("media_type")
    val mediaType: String?,
    @SerializedName("service_version")
    val serviceVersion: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?
)