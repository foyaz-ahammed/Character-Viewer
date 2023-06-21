package com.sample.simpsonsviewer.models

import kotlin.Exception

sealed class ResponseModel<T> {
    data class Success<T>(val value: T): ResponseModel<T>()
    data class Failure<T>(val exception: Exception): ResponseModel<T>()
}