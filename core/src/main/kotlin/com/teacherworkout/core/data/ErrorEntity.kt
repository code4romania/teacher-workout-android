package com.teacherworkout.core.data

sealed class ErrorEntity(val message: String?) {
    sealed class ApiError(message: String?): ErrorEntity(message) {
        object ServerError: ApiError("Server error")
        object NotFound: ApiError("Not found")
    }
}