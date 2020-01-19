package com.eco4ndly.awesomeimageviewer.infra.mapper.photos

import com.eco4ndly.awesomeimageviewer.infra.mapper.Mapper
import com.eco4ndly.awesomeimageviewer.main.uimodel.Photo
import com.eco4ndly.awesomeimageviewer.netwotk.apimodel.PhotoApi

/**
 * A Sayan Porya code on 2020-01-20
 */
class PhotosMapper(private val photoApiList: List<PhotoApi>) : Mapper<List<Photo>> {
    override fun getMapping(): List<Photo> {
        return photoApiList
            .map {
                Photo(
                    it.copyright?:"",
                    it.date?:"",
                    it.explanation?:"",
                    it.hdurl?:"",
                    it.mediaType?:"",
                    it.serviceVersion?:"",
                    it.title?:"",
                    it.url?:""
                )
            }.toList()
    }
}