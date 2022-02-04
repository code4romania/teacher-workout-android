package com.teacherworkout.core.data

sealed class Result<out T: Any> {
    data class Success<out T: Any>(val data: T) : Result<T>()
    data class Error(val error: ErrorEntity) : Result<Nothing>()
}
