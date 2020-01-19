package com.eco4ndly.awesomeimageviewer.infra.exception

import com.eco4ndly.awesomeimageviewer.infra.network.ErrorHandler

class NoResponseException(message: String? = ErrorHandler.UNKNOWN_ERROR) : Exception(message)
