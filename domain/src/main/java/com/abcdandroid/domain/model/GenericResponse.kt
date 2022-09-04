package com.abcdandroid.domain.model

sealed class GenericResponse<T>(result: kotlin.Result<T?>?) {
    class Loading<T> : GenericResponse<T>(null)
    data class Result<T>(val dataSource: DataSource, val result: kotlin.Result<T?>) : GenericResponse<T>(result = result)
}

